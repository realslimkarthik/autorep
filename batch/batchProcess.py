import MySQLdb as mdb
import sys
import requests
import json
import config as cf

def db_check():
    notifyJSON = {}
    try:
        con = mdb.connect(cf.HOST, cf.USERNAME, cf.PASSWORD, cf.DATABASE)
        cur = con.cursor()
        cur.execute("SELECT ID FROM STORE")
        stores = cur.fetchall()
        cur.execute("SELECT ID, MINSHELFQUANTITY FROM PRODUCT")
        productList = cur.fetchall()
        for i in stores:
            notifyJSON[str(i[0])] = []
            for j in productList:
                cur.execute("SELECT COUNT(" + str(j[0]) + ") FROM SKU WHERE STORE_ID=" + str(i[0]) + " AND PRODUCT_ID=" + str(j[0]))
                pIDCount = cur.fetchall()
                print str(i[0]) + ' has ' + str(j[0]) + ' in the store with ' + str(j[1] - pIDCount[0][0]) + ' items'
                if pIDCount[0][0] < j[1]:
                    suggObj = {str(j[0]): str(j[1] - pIDCount[0][0])}
                    notifyJSON[str(i[0])].append(suggObj)

    except mdb.Error, e:
        print "Error %d: %s" % (e.args[0], e.args[1])
        sys.exit(1)

    finally:
        if con:
            con.close()
    return notifyJSON


def notifyBackend(resultJSON):
    payload = json.dumps(resultJSON)
    r = requests.post("http://54.172.105.21/notify/", params=payload)
    print r.status_code

if __name__ == "__main__":
    resultJSON = db_check()
    print resultJSON
    notifyBackend(resultJSON)
