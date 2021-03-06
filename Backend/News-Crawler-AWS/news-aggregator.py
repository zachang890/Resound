import requests
from bs4 import BeautifulSoup
import json


def acquire_recent_news(url):
    def times_acquire(results, param):
        val = results.find_all('time', string=lambda text: param in text.lower())
        l = [time.text for time in val]
        return l, val

    source = requests.get(url)
    soup = BeautifulSoup(source.content, 'html.parser')

    search_results = soup.find('div', class_="lBwEZb BL5WZb xP6mwf")
    time_dict = {}
    time_list = ['minutes', 'hour', 'yesterday', 'days']
    iterate = 0
    while len(time_dict) < 3 and iterate < 4:
        times_addition, soup_obj_addition = times_acquire(search_results, time_list[iterate])
        for entry, soup_obj in zip(times_addition, soup_obj_addition):
            time_dict[entry] = soup_obj
            if len(time_dict) == 3:
                break
        iterate += 1
    article_link = {}
    time_source = {}
    for key in time_dict:
        header = time_dict[key].find_parent('div').find_parent('div').find_previous_sibling('h3')
        a_ref = header.find('a')
        article_link[a_ref.text] = "news.google.com" + a_ref['href'][1:]
        news_source = time_dict[key].find_parent('div').find('a').text
        time_source[key] = news_source
    return article_link, time_source


def news_by_topic(topic):
    d, t = acquire_recent_news("https://news.google.com/search?q="+topic+"&hl=en-US&gl=US&ceid=US%3Aen")
    to_return = "";
    for key, entry in zip(d.keys(), t.keys()):
        to_return = to_return + key + '\n' + d[key] + '\n' + t[entry] + '\n' + entry + '\n'
    return to_return

def main(topic):
    return news_by_topic(topic)

topic = ""
def lambda_handler(event, context):
    http_method = event['httpMethod']
    
    if http_method == "GET":
        topic = event['queryStringParameters']['topic']
    elif http_method == "POST":
        body = json.loads(event['body'])
        topic = body['topic']
        
    to_return = main(topic)
    return {
        "statusCode": 200,
        "body": json.dumps(to_return)
    }

