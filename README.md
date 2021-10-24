# SONAR
By Vanessa Gerber, Tiba Al Anssari, Kimberly Flores

## Inspiration
We all have had situations where we feel like we could possibly be in danger and typing out a text message to our loved ones may not be feasible. With that in mind, we created an app to make it easy to push out an alert to all of our friends from a click of a button, letting them know of your current location!

## What it does
By pressing the alert button, Sonar sends SMS messages to your friends and family letting them know of your current location with a google maps link containing your coordinates. You can manually add who you would like to send these alert messages to on our app by only inputting two simple pieces of information which are the person's name and phone number.

We believe Sonar is special because its provides a simple yet effective solution to something many of us face in our daily lives. Sonar is also different from apps like it because its gives the user full control of their security. Your location is only shared when you feel it is absolutely vital to hit the alert button. It is all about empowering you!

## How we built it
We built Sonar using android studio (which utilizes Java and XML), SMS, and Google Location Services. We also collaborated through Github!

## Challenges we ran into
We ran into issues with SMS and using the Google Location Services as it was a first for all of us. We were struggling to get the message receivers to see the alert messages at first as it was only sending from our end but we quickly managed to fix this issue! It also took us a minute to properly set up the user's permissions (such as location, send SMS, etc.) in order to have proper access to use them from our app.

## Accomplishments that we're proud of
We are proud of getting the SMS alerts to work how we wanted them to and for the message to include the proper coordinates of the user's location. We are also so proud of the concept because of the necessity it can provide in many of our daily lives. We definitely completed the majority of what we wanted the app to accomplish and managed our time effectively throughout the hackathon!

## What we learned

**Vanessa Gerber**
Despite the short 24 hours we had, I feel like I've learned so much from this experience! I handled tools I've never touched such as Google's location services which we used to get the exact coordinates of a person's location! I also learned how to query Parse objects through the Parse database which felt like invaluable information! I also took a deep dive into android development like I never have before honing my knowledge in everything from activities to fragments to parcelables and so much more!

**Tiba Al Anssari**
I learned how to send SMS messages through Android Studio. I learned so much about user permissions, callbacks, and intents in the process! I also got better at merging branches on GitHub and resolving merge conflicts! This hackathon also exposed me to some of the most common mobiles features like using Recycler Views for feeds and using fragments with navigation views to navigate our app!

**Kimberly Flores**
I learned about using XML to make user interfaces with android studio. I had no prior experience with making user interfaces or android studio so this was a challenge for me! Learning to use Figma to make a mock-up helped greatly with the design process. I also learned how to use Github for collaboration.

## What's next for Sonar
One of the main things we want and do hope to implement but opted out due to the time constraints is providing live visual updates with Google Cloud's Map API. We would like to add an "add photo option" to the friend's list and finish the edit profile functionalities so that the user can change their photo, phone number, name, and password as needed.
