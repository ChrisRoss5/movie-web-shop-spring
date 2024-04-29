CREATE TABLE MovieGenre
(
    id   INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(25) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE MovieAgeRating
(
    id   INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(25) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Movie
(
    id              INT GENERATED ALWAYS AS IDENTITY,
    genreId         INT           NOT NULL,
    ageRatingId     INT           NOT NULL,
    title           VARCHAR(50)   NOT NULL,
    description     VARCHAR(255)  NOT NULL,
    thumbnailUrl    VARCHAR(2048) NOT NULL,
    durationMinutes INT           NOT NULL,
    releaseDate     DATE          NOT NULL,
    price           DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (genreId) REFERENCES MovieGenre (id),
    FOREIGN KEY (ageRatingId) REFERENCES MovieAgeRating (id)
);

CREATE TABLE "User"
(
    id       INT GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(30)  NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Role
(
    id   INT GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(15) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE UserRole
(
    userId INT NOT NULL,
    roleId INT NOT NULL,
    PRIMARY KEY (userId, roleId),
    FOREIGN KEY (userId) REFERENCES "User" (id),
    FOREIGN KEY (roleId) REFERENCES Role (id)
);

CREATE TABLE Purchase
(
    id            INT GENERATED ALWAYS AS IDENTITY,
    userId        INT           NOT NULL,
    paymentMethod VARCHAR(25)   NOT NULL,
    purchaseDate  DATE          NOT NULL DEFAULT CURRENT_DATE,
    totalAmount   DECIMAL(8, 2) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (userId) REFERENCES "User" (id)
);

CREATE TABLE PurchaseMovie
(
    id         INT GENERATED ALWAYS AS IDENTITY,
    purchaseId INT NOT NULL,
    movieId    INT NOT NULL,
    quantity   INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (purchaseId) REFERENCES Purchase (id),
    FOREIGN KEY (movieId) REFERENCES Movie (id)
);