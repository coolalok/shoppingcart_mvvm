Developer
Alok Kulkarni :- alok.kulkarni10@gmail.com / 9975261781

Shopcart
This application provides a Cart management facility .
Products can be viewed according to their category.
Product details show information such as Product image , name , price.
Products can be added to / removed from the Cart.
Cart screen shows all products added to Cart along with individual and total price.
Cart items are persisted across multiple app runs.

Development Overview
Application follows MVVM architecture.
Android Data Binding has been extensively used.
Event notification between Model, View and ViewModel is done using EventBus library.
Test data is stored as part of assets directory in the form of JSON string and images.
Android Testing uses dependency injection using Dagger2 for some components
Cart is preserved using Json string stored in SharedPreferences.

Libraries used
AppCompat, CardView and RecyclerView
Data Binding
Picasso
Jackson
ButterKnife
EventBus
Dagger2
