import random
import MySQLdb as mdb
import sys
import requests
import json
import config as cf

def db_reduce():
    notifyJSON = {}
    try:
        con = mdb.connect(cf.HOST, cf.USERNAME, cf.PASSWORD, cf.DATABSE)
        cur = con.cursor()
        cur.execute("SELECT ID FROM STORE")
        stores = cur.fetchall()
        cur.execute("SELECT ID FROM PRODUCT")
        productList = cur.fetchall()

