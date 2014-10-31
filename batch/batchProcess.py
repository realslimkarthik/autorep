import MySQLdb as mdb
import sys
import requests
import json
#threshold = 100

def db_check():
    notifyJSON = {}
    try:
        con = mdb.connect('localhost', 'root', '', 'autorep')
        cur = con.cursor()
        cur.execute("SELECT ID FROM STORE")
        stores = cur.fetchall()
        cur.execute("SELECT ID, MINSHELFQUANTITY FROM PRODUCT")
        productList = cur.fetchall()
        for i in stores:
            for j in productList:
                cur.execute("SELECT COUNT(" + j[0] + ") FROM SKU WHERE STORE_ID=" + i[0])
                pIDCount = cur.fetchall()
                if pIDCount[0] < j[1]:
                    notifyJSON[i].append(j[0])

    except mdb.Error, e:
        print "Error %d: %s" % (e.args[0], e.args[1])
        sys.exit(1)

    finally:
        if con:
            con.close()


def notifyBackend(resultJSON):
    payload = json.dumps(resultJSON)
    r = requests.post("http://54.172.105.21/webservices/notify/", params=payload)

if __name__ == "__main__":
    resultJSON = db_check()
    notifyBackend(resultJSON)
