# Gallery

<img src="./screenshots/1.png" width = "264" height = "464"/> <img src="./screenshots/2.png" width = "264" height = "464"/> <img src="./screenshots/3.png" width = "264" height = "464"/> <img src="./screenshots/4.png" width = "264" height = "464"/>

## Implementation Details

Following are the features implemented;
1. The user can view the list of, search for and preview images from [Pixabay API](https://pixabay.com/api/docs/).
2. And **Retrofit** is used to fetch the data from the api which is then paginated using **Paging 3** with **Coroutine Flows**.
3. All the project dependencies are injected using **Hilt**
4. **Dark Theme** support for Android 10 and above
5. All the presentation is done using **Android Architecture Components** like ViewModels, LiveData and Data Binding.

## Project Setup
1. Clone the project and run it in Android studio.
2. Go to Pixabay developer page and generate an API key.
3. Copy the generated API key from your account details page.
4. Store the copied API key in your computers Environment Variables by name: __ImageApiKey__
5. You will also have to store the API base url in your computers Environment Variables by name: __ImageApiBaseUrl__
5. Build and Run the application \m/.