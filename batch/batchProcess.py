import random
import MySQLdb as mdb
import sys

try:
    con = mdb.connect('localhost', 'root', '', 'autorep')

    cur = con.cursor()
    cur.execute("SELECT ID FROM STORE")
    stores = cur.fetchall()
    cur.execute("SELECT DISTINCT(ID) FROM PRODUCT")
    productList = cur.fetchall()
    for i in stores:
        for j in productList:
            cur.execute("SELECT COUNT(" + j[0] + ") FROM SKU WHERE ID=" + i[0])
            pIDCount = cur.fetchall()
            if pIDCount[0] < threshold:
                notifyJSON[i].append(j)

    cur.execute("SHOW TABLES")
    result = cur.fetchall()
    for i in result:
        print i[0]

except mdb.Error, e:

    print "Error %d: %s" % (e.args[0], e.args[1])
    sys.exit(1)

finally:

    if con:
        con.close()

