import firebase_admin
import time
from datetime import datetime
from firebase_admin import credentials, messaging


def send_to_topic():
    # [START send_to_topic]
    # The topic name can be optionally prefixed with "/topics/".
    topic = 'bellNotifications'

    dateTimeObj = datetime.now()
    timeObj = dateTimeObj.time()

    # See documentation on defining a message payload.
    message = messaging.Message(
        notification=messaging.Notification(
            title='Rrring!',
            body='Someone ringed your door at {}:{}:{}'.format(timeObj.hour, timeObj.minute, timeObj.second)
        ),

        topic=topic,
    )

    # Send a message to the devices subscribed to the provided topic.
    response = messaging.send(message)
    # Response is a message ID string.
    print('Successfully sent message:', response)
    # [END send_to_topic]

cred = credentials.Certificate("bell_server_key.json")
app = firebase_admin.initialize_app(cred)

while True:
    send_to_topic()
    time.sleep(10.0)
