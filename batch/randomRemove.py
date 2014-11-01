import random
import MySQLdb as mdb
import sys
import requests
import json
import config as cf

def db_reduce():
    notifyJSON = {'data': {}}
    stores = []
    productList = []
    try:
        con = mdb.connect(cf.HOST, cf.USERNAME, cf.PASSWORD, cf.DATABASE)
        cur = con.cursor()
        cur.execute("SELECT ID FROM STORE")
        for i in cur.fetchall():
            stores.append(i[0])
        cur.execute("SELECT ID FROM PRODUCT")
        for i in cur.fetchall():
            productList.append(i[0])
        randSID = random.randrange(0, len(stores))
        randPID = random.randrange(0, len(productList))
        randQ = random.randrange(0, 21)
        randStore = stores[randSID]
        randProd = productList[randPID]
        print randStore
        print randProd
        print randQ
        cur.execute("SELECT ID, PRODUCT_ID FROM SKU WHERE STORE_ID=" + str(randStore) + " AND PRODUCT_ID=" + str(randProd) + " LIMIT " + str(randQ))
        purchasedItems = cur.fetchall()
        notifyJSON['data'] = {'prodList': [],'store': str(randStore)}
        for i in purchasedItems:
            #cur.execute("DELETE FROM SKU WHERE ID=" + str(i[0]))
            notifyJSON['data']['prodList'].append({'item':str(i[0]), 'product': str(i[1])})
    except mdb.Error, e:
        print "Error %d: %s" % (e.args[0], e.args[1])
        sys.exit(1)

    finally:
        if con:
            con.close()

    return notifyJSON

def notifyBackend(resultJSON):
    payload = json.dumps(resultJSON)
    print payload
    r = requests.post('http://54.172.105.21/autorep', params=payload)
    print r.status_code


if __name__ == '__main__':
    resultJSON = db_reduce()
    notifyBackend(resultJSON)
