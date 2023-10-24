# Market Tracker Project

Market Tracker is a Spring Boot application that provides access to financial information. This application fetches data about stocks and cryptocurrencies from various APIs and provides users with real-time news.

## Project Structure

The project follows this package structure:

- **config**
  - ModelMapperConfig.java
  - OpenApiConfig.java (See details below)
  - RestTemplateConfig.java
  - SecurityConfig.java (See details below)
  
- **controller**
  - AssetController.java
  - CryptoController.java
  - StockController.java
  - UserController.java (See details below)

- **dto**
  - AssetInfoDTO.java
  - CryptoDTO.java
  - FeedItemDTO.java
  - GainersAndLosersDTO (See details below)
  - GlobalQuoteDTO (See details below)
  - NewsAndSentimentDTO (See details below)
  - StockDTO.java
  - TickerSentimentDTO.java
  - TopicDTO.java
  - UserDTO.java

- **enums**
  - AlphavantageApiFunctionType.java

- **model**
  - Crypto.java
  - Stock.java
  - User.java

- **repository**
  - CryptoRepository.java
  - StockRepository.java
  - UserRepository.java

- **request**
  - ChangePasswordRequest.java (See details below)
  - LoginRequest.java (See details below)
  - NewsAndSentimentRequest.java
  - UserRequest.java (See details below)
  - VerificationRequest.java (See details below)

- **service**
  - AssetService.java
  - CryptoService.java
  - StockService.java
  - UserService.java

- **util**
  - **api**
    - AlphavantagaAPI.java (See details below)
    - CoinPaprikaAPI.java (See details below)
    - EmailService.java (See details below)
  - **codeGeneration**
    - CodeGenerator.java (See details below)
  - **component**
    - **startup**
      - DataInitializationRunner.java (See details below)
    - **task**
      - CryptoTask.java (See details below)
      - ScheduledTask.java (See details below)
      - StockTask.java (See details below)
    - **urlBuilder**
      - AlphavantageApiUrlBuilder.java (See details below)
  - **mapper**
    - MapperUtil.java (See details below)
  - **responseHandler**
    - ResponseHandler.java (See details below)
  - **serializerDeserializer**
    - GainersAndLosersDTODeserializer.java (See details below)
    - GlobalQuoteDTODeserializer.java (See details below)
    - NewsAndSentimentDTODeserializer.java (See details below)

- **MarketTrackerApplication.java**

- **resources**
  - application.properties

This structure outlines the main components of the project and their respective files. You can use this as a reference to navigate and understand the project's organization.

## OpenAPI Configuration (OpenApiConfig.java)

The `OpenApiConfig` class configures the OpenAPI documentation for the Market Tracker application. The OpenAPI documentation is accessible via the following URLs:
- [Swagger UI](http://localhost:8080/swagger-ui/index.html#/): A developer-friendly interface.
- [JSON Document](http://localhost:8080/v3/api-docs): API documentation in JSON format.

The `OpenApiConfig` class contains the following configurations:

- `@OpenAPIDefinition`: Specifies general information about the API, including contact details, description, title, and version.
  - `@Contact`: Provides contact information for the API.
  - `@Info`: Contains general information about the API.

- `@Server`: Defines servers for the API, including a description and the base URL of the server.

## Security Configuration (SecurityConfig.java)

The `SecurityConfig` class configures security for the Market Tracker application. It includes the following configurations:

- `@Configuration`: Indicates that this class is a configuration class.
- `@EnableWebSecurity`: Enables Spring Security for the application.

The `SecurityConfig` class contains the following security configurations:

- `@Bean`: Defines a Bean for BCryptPasswordEncoder, which is used for password encoding.

- `@Bean`: Defines a `SecurityFilterChain` for configuring security. This security filter chain includes the following configurations:
  - Disables Cross-Site Request Forgery (CSRF) protection.
  - Authorizes all HTTP requests, allowing any request to be permitted.

These security configurations are designed to ensure that the application is secure while allowing any request to be permitted.

## User Controller (UserController.java)

The `UserController` class is responsible for handling user-related operations, including user retrieval, registration, login, logout, password reset, password change, information update, account verification, and changing account verification status.

The following are the available endpoints in the `UserController` class:

- `GET /user/{userId}`: Retrieves a user by their ID.
- `POST /user/register`: Registers a new user.
- `POST /user/login`: Logs in a user.
- `GET /user/logout/{userId}`: Logs out a user.
- `POST /user/forgotPassword/{userId}`: Initiates a password reset request.
- `POST /user/changePassword`: Changes a user's password.
- `PUT /user/update/{userId}`: Updates user information.
- `POST /user/verify/{userId}`: Verifies a user's account using a verification code.
- `PUT /user/changeVerified`: Changes a user's account verification status.

These endpoints handle various user-related functionalities, providing RESTful API access to user operations.

### User Request Models

The `UserController` uses the following request models for different operations:

- `UserRequest`: Used for user registration, updating user information, and other user-related operations.
  - `username`: The user's username.
  - `email`: The user's email address.
  - `password`: The user's password.

- `LoginRequest`: Used for user login.
  - `usernameOrEmail`: The username or email of the user.
  - `password`: The user's password.

- `ChangePasswordRequest`: Used for changing the user's password.
  - `resetToken`: The reset token received during the password reset process.
  - `newPassword`: The new password to be set.

- `VerificationRequest`: Used for verifying a user's account.
  - `verificationCode`: The verification code used to confirm the user's account.

These request models help in transferring data for various user-related operations.

## Gainers and Losers DTO (GainersAndLosersDTO.java)

The `GainersAndLosersDTO` class is a Data Transfer Object (DTO) that represents data related to top gainers, top losers, and most actively traded assets. This DTO class is annotated with `@JsonDeserialize` to specify custom deserialization using the `GainersAndLosersDTODeserializer`.

The `GainersAndLosersDTO` class includes the following fields:

- `metadata`: Contains metadata information.
- `lastUpdated`: Represents the last update timestamp.
- `topGainers`: A list of top gaining assets, each represented as an `AssetInfoDTO`.
- `topLosers`: A list of top losing assets, each represented as an `AssetInfoDTO`.
- `mostActivelyTraded`: A list of most actively traded assets, each represented as an `AssetInfoDTO`.

### Custom JSON Deserializer (GainersAndLosersDTODeserializer)

The `GainersAndLosersDTODeserializer` class is a custom JSON deserializer for converting JSON data into a `GainersAndLosersDTO` object. It performs the following tasks:

- Deserializes JSON data into a `GainersAndLosersDTO` object.
- Extracts and maps data from the JSON structure into the corresponding fields of the DTO.
- Handles the conversion of nested `AssetInfoDTO` objects within the top gainers, top losers, and most actively traded lists.

## Global Quote DTO (GlobalQuoteDTO.java)

The `GlobalQuoteDTO` class is another Data Transfer Object (DTO) that represents data related to global quotes for a specific asset. This DTO class is annotated with `@JsonDeserialize` to specify custom deserialization using the `GlobalQuoteDTODeserializer`.

The `GlobalQuoteDTO` class includes the following fields:

- `symbol`: Represents the asset's symbol.
- `open`: Indicates the opening price.
- `high`: Indicates the highest price during the trading day.
- `low`: Indicates the lowest price during the trading day.
- `price`: Represents the current price.
- `volume`: Represents the trading volume.
- `latestTradingDay`: Indicates the latest trading day.
- `previousClose`: Indicates the previous closing price.
- `change`: Indicates the price change.
- `changePercent`: Indicates the percentage change.

### Custom JSON Deserializer (GlobalQuoteDTODeserializer)

The `GlobalQuoteDTODeserializer` class is a custom JSON deserializer for converting JSON data into a `GlobalQuoteDTO` object. It performs the following tasks:

- Deserializes JSON data into a `GlobalQuoteDTO` object.
- Extracts and maps data from the JSON structure into the corresponding fields of the DTO.

## News and Sentiment DTO (NewsAndSentimentDTO.java)

The `NewsAndSentimentDTO` class is another Data Transfer Object (DTO) used for representing news and sentiment-related data. This class is annotated with `@JsonDeserialize` to specify custom deserialization using the `NewsAndSentimentDTODeserializer`.

The `NewsAndSentimentDTO` class includes the following fields:

- `items`: Contains information related to news and sentiment.
- `sentimentScoreDefinition`: Represents the sentiment score definition.
- `relevanceScoreDefinition`: Indicates the relevance score definition.
- `feed`: A list of feed items, each represented as a `FeedItemDTO`.

### Custom JSON Deserializer (NewsAndSentimentDTODeserializer)

The `NewsAndSentimentDTODeserializer` class is a custom JSON deserializer for converting JSON data into a `NewsAndSentimentDTO` object. It performs the following tasks:

- Deserializes JSON data into a `NewsAndSentimentDTO` object.
- Extracts and maps data from the JSON structure into the corresponding fields of the DTO.
- Handles the conversion of nested `FeedItemDTO` objects within the feed list.

The deserializer processes the following fields for each `FeedItemDTO`:

- `title`: Title of the feed item.
- `url`: URL link to the full feed.
- `timePublished`: Time when the feed item was published.
- `summary`: A brief summary of the feed item.
- `bannerImage`: Image associated with the feed.
- `source`: Source of the feed.
- `categoryWithinSource`: Category within the source.
- `sourceDomain`: Domain of the source.
- `topics`: A list of topics associated with the feed, each represented as a `TopicDTO`.
- `overallSentimentScore`: Overall sentiment score for the feed item.
- `overallSentimentLabel`: Label describing the overall sentiment.
- `tickerSentiment`: A list of sentiment scores for specific tickers, each represented as a `TickerSentimentDTO`.

### Nested DTOs

The `NewsAndSentimentDTO` includes nested DTOs, namely `FeedItemDTO`, `TopicDTO`, and `TickerSentimentDTO`, which are used to represent different aspects of the feed items and sentiment data.

## AlphaVantage API (AlphavantageAPI.java)

The `AlphavantageAPI` class is responsible for making requests to the Alpha Vantage API to retrieve stock and financial data. It uses the provided `RestTemplate` to send HTTP requests to the API endpoints. The main methods for fetching data are as follows:

- `fetchStocks()`: Fetches a list of stock data from the Alpha Vantage API. It sends an HTTP GET request to the API, retrieves CSV data, and parses it to create a list of `Stock` objects.

- `getGlobalQuote(String symbol)`: Fetches global quote data for a specific stock symbol. It sends an HTTP GET request to the API, processes the JSON response, and returns a `GlobalQuoteDTO` object.

- `getTopGainersAndLosers()`: Fetches data for the top gainers and losers. It sends an HTTP GET request to the API, processes the JSON response, and returns a `GainersAndLosersDTO` object.

- `getNewsAndSentiment(NewsAndSentimentRequest newsAndSentimentRequest)`: Fetches news and sentiment data using the provided request parameters. It sends an HTTP GET request to the API, processes the JSON response, and returns a `NewsAndSentimentDTO` object.

## CoinPaprika API (CoinPaprikaAPI.java)

The `CoinPaprikaAPI` class is responsible for making requests to the CoinPaprika API to retrieve cryptocurrency data. It uses the provided `RestTemplate` to send HTTP requests to the API endpoint. The main method for fetching data is as follows:

- `fetchCryptos()`: Fetches a list of cryptocurrency data from the CoinPaprika API. It sends an HTTP GET request to the API and processes the JSON response to create a list of `Crypto` objects.

## Email Service (EmailService.java)

The `EmailService` class is used for sending email notifications. It utilizes the `JavaMailSender` for creating and sending emails. It includes the following methods:

- `sendPasswordResetEmail(String to, String resetToken)`: Sends a password reset email to the specified recipient's email address, including the reset token.

- `sendVerificationCodeEmail(String to, String verificationCode)`: Sends an email containing the account verification code to the specified recipient's email address.

## Code Generator (CodeGenerator.java)

The `CodeGenerator` class is responsible for generating random codes. It is often used for generating verification codes or unique identifiers in the application. The class provides the following functionality:

- `generateCode()`: Generates a random code of a specified length using the characters allowed in the `ALLOWED_CHARACTERS` string. By default, it generates a code with a length of 6 characters containing uppercase letters (A-Z) and numbers (0-9).

This class uses a secure random number generator to ensure the randomness of the generated codes. The generated codes can be used for various purposes, such as account verification, password reset, or any scenario where unique codes are required.

## Data Initialization Runner (DataInitializationRunner.java)

The `DataInitializationRunner` class is a Spring component that implements the `ApplicationRunner` interface. It is responsible for initializing data when the application starts. This class plays a critical role in ensuring that essential data, such as stock and cryptocurrency codes, is available in the application's database.

The `DataInitializationRunner` class provides the following functionality:

- Checks if stock data is available in the database. If not, it runs the `StockTask` to update the stock data and logs a success message.
- Checks if cryptocurrency data is available in the database. If not, it runs the `CryptoTask` to update the cryptocurrency data and logs a success message.

By performing these checks and tasks during application startup, the `DataInitializationRunner` ensures that the application's data is up-to-date and available for use.

This class is part of the application's data initialization process and contributes to its smooth operation.

## Crypto Task (CryptoTask.java)

The `CryptoTask` class is a Spring component responsible for updating cryptocurrency data in the application's database. It retrieves a list of cryptocurrencies from an external API using the `CoinPaprikaAPI` service and saves them in the database using the `CryptoService`.

The class provides the following functionality:

- Fetches cryptocurrency data from an external API using the `CoinPaprikaAPI` service.
- Saves the fetched cryptocurrency data to the database using the `CryptoService`.

This task ensures that the application's cryptocurrency data is up-to-date and available for use.

## Scheduled Tasks (ScheduledTasks.java)

The `ScheduledTasks` class is responsible for scheduling recurring tasks within the application. It uses Spring's `@Scheduled` annotation to define a schedule for updating data periodically. In this case, it schedules the `updateData` method to run once every 10 days.

The class provides the following functionality:

- Schedules the `updateData` method to run at specific intervals.
- Calls the `updateCryptos` method in the `CryptoTask` to update cryptocurrency data.
- Calls the `updateStockData` method in the `StockTask` to update stock data.

Scheduling these tasks ensures that both cryptocurrency and stock data are regularly refreshed and kept current.

## Stock Task (StockTask.java)

The `StockTask` class is a Spring component responsible for updating stock data in the application's database. It retrieves a list of stock data from an external API using the `AlphavantageAPI` service and saves them in the database using the `StockService`.

The class provides the following functionality:

- Fetches stock data from an external API using the `AlphavantageAPI` service.
- Saves the fetched stock data to the database using the `StockService`.

This task ensures that the application's stock data is up-to-date and available for use.

## Alphavantage API URL Builder (AlphavantageApiUrlBuilder.java)

The `AlphavantageApiUrlBuilder` class is a Spring component responsible for constructing the URL for Alpha Vantage API requests. It is used to build API URLs based on the specified function type, symbol (for some functions), and request parameters (specifically for the NEWS_SENTIMENT function).

The class provides the following functionality:

- Constructs the URL for Alpha Vantage API requests.
- Supports various API functions such as listing status, global quote, news sentiment, and top gainers/losers.
- Incorporates the API base URL and API key for secure and authenticated requests.
- Allows dynamic configuration of request parameters based on the function type.

This URL builder class is an essential component for preparing API requests to Alpha Vantage, ensuring that the appropriate API endpoint is constructed based on the requested functionality.

## Mapper Utility (MapperUtil.java)

The `MapperUtil` class is a utility component responsible for mapping between entity and DTO objects in your application. It uses the ModelMapper library for the conversion process. This class provides two main methods:

- `convertToDTO`: Converts an entity to a DTO.
- `convertToEntity`: Converts a DTO to an entity.

These methods facilitate the process of transforming data between entity and DTO objects, making it easier to work with data from the database and present it to users through API responses or other parts of the application.

## Response Handler (ResponseHandler.java)

The `ResponseHandler` class is a utility class that simplifies the creation of HTTP responses in your application. It provides methods for creating both successful and error responses.

- `successResponse`: Used to create a successful response. It includes the HTTP response status, an informational message, and the response data.

- `errorResponse`: Used to create error responses. It includes the HTTP response status and an error message.

The `ResponseHandler` class abstracts the process of constructing response entities and is particularly helpful when standardizing the format of API responses throughout your application. It ensures consistent and clear response structures for both successful and error cases.

## Used APIs

The project utilizes the following APIs:

- [Alpha Vantage](https://www.alphavantage.co/): Used for fetching stock data and news.
- [CoinPaprika](https://coinpaprika.com/): Used for fetching cryptocurrency data.