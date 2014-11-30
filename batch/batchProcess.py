import MySQLdb as mdb
import sys
import requests
import json
import config as cf

def db_check():
    notifyJSON = {'suggestions': []}
    try:
        con = mdb.connect(cf.HOST, cf.USERNAME, cf.PASSWORD, cf.DATABASE)
        cur = con.cursor()
        cur.execute("SELECT ID FROM autorep2.STORE")
        stores = cur.fetchall()
        cur.execute("SELECT ID, MINSHELFQUANTITY FROM autorep2.PRODUCT
        productList = cur.fetchall()
        for i in stores:
            innerJSON = {}
            innerJSON['prodList'] = []
            for j in productList:
                cur.execute("SELECT COUNT(ID) FROM autorep2.SKU WHERE STORE_ID=" + str(i[0]) + " AND PRODUCT_ID=" + str(j[0]) + " limit 5")
                pIDCount = cur.fetchall()
                if pIDCount[0][0] != 0:
                    if pIDCount[0][0] < j[1]:
                        suggObj = {'product':str(j[0]), 'count': str(j[1] - pIDCount[0][0])}
                        innerJSON['prodList'].append(suggObj)
                else:
                    suggObj = {'product': str(j[0]), 'count': str(j[1])}
                    innerJSON['prodList'].append(suggObj)
            if innerJSON['prodList'] != []:
                innerJSON['store'] = str(i[0])
                notifyJSON['suggestions'].append(innerJSON)

    except mdb.Error, e:
        print "Error %d: %s" % (e.args[0], e.args[1])
        sys.exit(1)

    finally:
        if con:
            con.close()
            if cur:
                cur.close()
    retJSON = {'data': notifyJSON}
    return retJSON


def notifyBackend(resultJSON):
    payload = json.dumps(resultJSON)
    headers = {'Content-Type': 'application/x-www-form-urlencoded'}
    print payload
    r = requests.post(cf.NOTIFY_URL, params=payload, headers=headers)
    print r.status_code
    print r.text

if __name__ == "__main__":
    resultJSON = db_check()
    notifyBackend(resultJSON)
