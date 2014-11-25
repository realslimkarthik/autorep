import random
import MySQLdb as mdb
import sys
import requests
import json
import config as cf

def db_reduce(pid, quant):
    reduceJSON = {}
    try:
        con = mdb.connect(cf.HOST, cf.USERNAME, cf.PASSWORD, cf.DATABASE)
        cur = con.cursor()
        
        cur.execute("SELECT ID, PRODUCT_ID FROM SKU WHERE STORE_ID=" + str(randStore) + " AND PRODUCT_ID=" + str(randProd) + " LIMIT " + str(randQ))
        purchasedItems = cur.fetchall()
        count = 0
        # for i in purchasedItems:
        #     #cur.execute("DELETE FROM SKU WHERE ID=" + str(i[0]))
        
        reduceJSON['product'] = randPID
        reduceJSON['count'] = len(purchasedItems)
    except mdb.Error, e:
        print "Error %d: %s" % (e.args[0], e.args[1])
        sys.exit(1)

    finally:
        if con:
            con.close()

    return reduceJSON

def notifyBackend(resultJSON):
    payload = json.dumps(resultJSON)
    header = {'Content-Type': 'application/x-www-form-urlencoded'}

    print payload
    #r = requests.post(cf.REDUCE_URL, params=payload, headers=header)
    #r = requests.post('http://54.172.105.21/tranlogs', params=payload, headers=header)
    r = requests.post('http://10.0.0.9:8080/autorep/tranlogs/', params=payload, headers=header)

    print r.status_code

def deferNotify():
    pass

if __name__ == '__main__':
    num = random.randrange(0, 3)
    resultJSON = {'salesData': {'details': [] }}
    innerJSON = {}
    stores = []
    productList = []
    try:
        con = mdb.connect(cf.HOST, cf.USERNAME, cf.PASSWORD, cf.DATABASE)
        cur = con.cursor()
        cur.execute("SELECT ID FROM STORE")
        for i in cur.fetchall():
            stores.append(i[0])
        randSID = random.randrange(0, len(stores))
        randStore = stores[randSID]
        resultJSON['salesData'] = { 'details': []}
        innerJSON = {'prodList': [], 'store': str(randStore)}

        cur.execute("SELECT ID FROM PRODUCT")
        for i in cur.fetchall():
            productList.append(i[0])
        for i in range(0, num):
            if len(productList) < 2:
                break
            randPID = random.randrange(1, len(productList))
            randQ = random.randrange(0, 21)
            randProd = productList[randPID]
            productList.pop(randPID)
            innerJSON['prodList'].append(db_reduce(randPID, randQ))

        if len(innerJSON['prodList']) == 0:
            deferNotify()
        else:
            resultJSON['salesData']['details'].append(innerJSON)
            notifyBackend(resultJSON)
    except mdb.Error, e:
        print "Error %d: %s" % (e.args[0], e.args[1])
        sys.exit(1)

    finally:
        if con:
            con.close()
