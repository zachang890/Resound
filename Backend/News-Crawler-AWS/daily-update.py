import requests
import json

def lambda_handler(event, context):
    requests.put(
        "http://dynamo-access.eba-24mqhk9g.us-west-2.elasticbeanstalk.com/dynamo/suggestions/revisit-today-topics")
    response = requests.get(
        "http://backend-main.eba-24mqhk9g.us-west-2.elasticbeanstalk.com/suggestions/today-topics-list")
    json_data = response.json()

    for topic in json_data['list']:
        requests.put("http://dynamo-access.eba-24mqhk9g.us-west-2.elasticbeanstalk.com/dynamo/revisit-details/" + topic)
    return {
        "statusCode": 200,
        "body": json.dumps(json_data['list'])
    }