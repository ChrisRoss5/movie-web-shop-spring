-- Insert default movie genres
INSERT INTO MovieGenre (name)
VALUES ('ACTION');
INSERT INTO MovieGenre (name)
VALUES ('COMEDY');
INSERT INTO MovieGenre (name)
VALUES ('DRAMA');
INSERT INTO MovieGenre (name)
VALUES ('HORROR');

-- Insert default movie age ratings
INSERT INTO MovieAgeRating (name)
VALUES ('G');
INSERT INTO MovieAgeRating (name)
VALUES ('PG');
INSERT INTO MovieAgeRating (name)
VALUES ('PG_13');
INSERT INTO MovieAgeRating (name)
VALUES ('R');

-- Insert default movies
INSERT INTO Movie (genreId, ageRatingId, title, description, thumbnailUrl, durationMinutes, releaseDate, price)
VALUES (1, 2, 'The Avengers', 'Superhero team-up movie',
        'https://wallpapers.com/images/featured/avengers-vm16xv4a69smdauy.jpg', 143, '2012-05-04', 14.99);
INSERT INTO Movie (genreId, ageRatingId, title, description, thumbnailUrl, durationMinutes, releaseDate, price)
VALUES (2, 3, 'The Hangover', 'Comedy about a wild bachelor party',
        'https://wallpapercave.com/wp/wp2525816.jpg', 100, '2009-06-05', 9.99);
INSERT INTO Movie (genreId, ageRatingId, title, description, thumbnailUrl, durationMinutes, releaseDate, price)
VALUES (3, 4, 'The Shawshank Redemption', 'Drama about hope and friendship in prison',
        'https://wallpapercave.com/wp/wp2014276.jpg', 142, '1994-09-23', 12.99);

-- Insert default users (password: 3)
INSERT INTO "User"(username, password)
VALUES ('u', '$2a$10$OI1MEzYoEwX.1szvETEsrOIt09N.V9x/GJ9BKiaCDuqGxlCRtRgVK'); -- user
INSERT INTO "User"(username, password)
VALUES ('3', '$2a$10$OI1MEzYoEwX.1szvETEsrOIt09N.V9x/GJ9BKiaCDuqGxlCRtRgVK'); -- admin
INSERT INTO "User"(username, password)
VALUES ('ro', '$2a$10$OI1MEzYoEwX.1szvETEsrOIt09N.V9x/GJ9BKiaCDuqGxlCRtRgVK'); -- read-only

-- Insert default roles
INSERT INTO Role(name)
VALUES ('USER');
INSERT INTO Role(name)
VALUES ('ADMIN');
INSERT INTO Role(name)
VALUES ('READ_ONLY');

-- Assign roles to users
INSERT INTO UserRole(userId, roleId)
VALUES (1, 1);
INSERT INTO UserRole(userId, roleId)
VALUES (2, 1);
INSERT INTO UserRole(userId, roleId)
VALUES (2, 2);
INSERT INTO UserRole(userId, roleId)
VALUES (3, 3);