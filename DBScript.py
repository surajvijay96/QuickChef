import requests
import json
import mysql.connector
mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  passwd="root",
  database="Quickchef"
)
mycursor = mydb.cursor()

business_id = ""

API_KEY = "q4RM_cQcKxK-Qx5cMJFcmINjpFGFqgUMSoVeaBBd0pWe7RL616efHNaXlHcEJPf5Rx7K5WV9nesISyQ35qIfOjqX90UIHN4GYaWNjEXXYx"
ENDPOINT = 'https://api.yelp.com/v3/businesses/search'
HEADERS = {'Authorization': 'bearer %s' % API_KEY}

PARAMETERS = {'term': 'food',
                'limit': 50,
             'location': 'New York'}

response = requests.get(url = ENDPOINT,
                        params = PARAMETERS,
                        headers = HEADERS)

business_data = response.json()
# print(business_data)
for i in business_data['businesses']:
    address =' '.join(i['location']['display_address'])
    sql = "INSERT INTO restaurants (name, image_url,rating,latitude,longitude,address) VALUES (%s, %s,%s, %s,%s, %s)"
    val = (i['name'], i['image_url'],i['rating'],i['coordinates']['latitude'],i['coordinates']['longitude'],address)
    mycursor.execute(sql, val)
    mydb.commit()
    print(mycursor.rowcount, "record inserted.")


    # print ("Name:", i['name'])
    # print ("Image URL:", i['image_url'])
    # print ("Rating:", i['rating'])
    # print("coordinates-latitude",i['coordinates']['latitude'])
    # print("coordinates-longitude",i['coordinates']['longitude'])
    # address =' '.join(i['location']['display_address'])
    # print("Address",address)
    # print ()


