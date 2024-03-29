# SpaceX Launch Calendar

## The Inspiration 
I am a fan of SpaceX so sometimes, time permitting I try to watch the launches. I would like to watch more but this requiers me to stay up to date with the latest launch dates. At times, because of my schedule, I'll miss a launch date so I thought about creating an app that would alert me about launch times and dates. 

## Requirements
My plan is to have a view that will be a calendar with launch dates and times on it. When clicked on a date that a launch is on, a different view will come up with more details on the specific launch. A user will have the option of making an alarm that will alert them of the upcoming launch on the day of. 

I'm planning on using a database to hold all the upcoming launch dates for the year and retreiving them at app start. 

The app will talk to an API that has all the SpaceX launch dates. The app wil retreive the dates from this external API and store them in the database that I mentioned earlier. 

## User Interface

This app will be designed to work the same way in landscape and portrait mode. I will utilize everything I learned in previous projects to create the appropriate activities for both layouts(portrait and landscape) so the user has a seamless experience no matter which orientation the phone is in. Below is a rough draft of what I envision the app to look like in its entirety. 

![Alt text](space_x.jpg?raw=true "space_x")

## Data Persistence
Im planning on having a table with user input that will get saved in a table to keep the input from the users.
    
To test this, I will have to shut down using onDestroy() event, and then restart to see if the previously entered data still shows up.

    

### Communication

My app will utilize the SpaceX API for requests of launch dates. Once the app has received the dates it will store them in a table on the database. The app will use this data to populate the calendar with appropriate dates. The app also has a button that will make another request to the API upon an onClick() event that will update the date table if there happens to be a change in the dates from the API. 
