TRUNCATE TABLE player, manager, club RESTART IDENTITY CASCADE;

-- ====== CLUBES ======
INSERT INTO club (id, name, established_date, stadium_name, city, league, titles_won)
VALUES (1, 'FC Barcelona', '1899-11-29', 'Spotify Camp Nou', 'Barcelona', 'LA_LIGA', 27),
       (2, 'Real Madrid CF', '1902-03-06', 'Santiago Bernabéu', 'Madrid', 'LA_LIGA', 36),
       (3, 'Atlético de Madrid', '1903-04-26', 'Cívitas Metropolitano', 'Madrid', 'LA_LIGA', 11);

-- ====== MANAGERS ======
INSERT INTO manager (id, name, nationality, date_of_birth, titles_won, club_id)
VALUES (1, 'Hansi Flick', 'Germany', '1965-02-24', 8, 1),
       (2, 'Carlo Ancelotti', 'Italy', '1959-06-10', 26, 2),
       (3, 'Diego Simeone', 'Argentina', '1970-04-28', 10, 3);

-- ====== PLAYERS ======
INSERT INTO player (id, name, position, jersey_number, date_of_birth, nationality, club_id)
VALUES
-- Barça
(1, 'Robert Lewandowski', 'FORWARD', 9, '1988-08-21', 'Poland', 1),
(2, 'Pedri González', 'MIDFIELDER', 8, '2002-11-25', 'Spain', 1),
(3, 'Frenkie de Jong', 'MIDFIELDER', 21, '1997-05-12', 'Netherlands', 1),
(4, 'Ronald Araújo', 'DEFENDER', 4, '1999-03-07', 'Uruguay', 1),
(5, 'Marc-André ter Stegen', 'GOALKEEPER', 1, '1992-04-30', 'Germany', 1),

-- Real Madrid
(6, 'Vinícius Júnior', 'FORWARD', 7, '2000-07-12', 'Brazil', 2),
(7, 'Jude Bellingham', 'MIDFIELDER', 5, '2003-06-29', 'England', 2),
(8, 'Rodrygo Goes', 'FORWARD', 11, '2001-01-09', 'Brazil', 2),
(9, 'Antonio Rüdiger', 'DEFENDER', 22, '1993-03-03', 'Germany', 2),
(10, 'Thibaut Courtois', 'GOALKEEPER', 1, '1992-05-11', 'Belgium', 2),

-- Atlético de Madrid
(11, 'Álvaro Morata', 'FORWARD', 9, '1992-10-23', 'Spain', 3),
(12, 'Antoine Griezmann', 'FORWARD', 7, '1991-03-21', 'France', 3),
(13, 'Koke Resurrección', 'MIDFIELDER', 6, '1992-01-08', 'Spain', 3),
(14, 'José María Giménez', 'DEFENDER', 2, '1995-01-20', 'Uruguay', 3),
(15, 'Jan Oblak', 'GOALKEEPER', 13, '1993-01-07', 'Slovenia', 3);
