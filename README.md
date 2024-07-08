# ProductApp

Rest API enpoints:

Get all the articles from Guardian and New York Times:
--- /api/news?page=1

Get articles from Guardian API:
--- /api/news/guardian-news?page=1

Get articles from New York Times API:
--- /api/news/times-news?page=1

Get articles filtered by search key word:
--- /api/news?page=1&seachKeyword=election
--- /api/news/guardian-news?page=1&seachKeyword=football
--- /api/news/times-news?page=1&seachKeyword=election

Building docker images individually:
<!-- Backend Image -->
cd NewsApp
docker build -t product-backend-image .


Run docker-compose mannually for testing:
docker-compose up -d
docker-compose down


Some of the mostly used principles kept in mind while designing this News Search Application are: 

<!-- SOLID Principles used for backend: -->

Single Responsibility Principle (SRP) - To make sure the each class has a single reason to change

Open/Closed Principle (OCP): Used interface to allow classes implementing it modification, without changing the logic.

TODO:

Integrate Swagger for better API documentation.
