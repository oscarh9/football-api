INSERT INTO clubs (name, established_date, stadium_name, city, league, titles_won)
VALUES
  ('FC Barcelona', '1899-11-29', 'Spotify Camp Nou', 'Barcelona', 'LA_LIGA', 27),
  ('Real Madrid CF', '1902-03-06', 'Santiago Bernabéu', 'Madrid', 'LA_LIGA', 36),
  ('Atlético de Madrid', '1903-04-26', 'Cívitas Metropolitano', 'Madrid', 'LA_LIGA', 11);

INSERT INTO managers (name, nationality, date_of_birth, titles_won, club_id)
VALUES
  ('Hansi Flick', 'German', '1965-02-24', 10, (SELECT id FROM clubs WHERE name = 'FC Barcelona')),
  ('Carlo Ancelotti', 'Italian', '1959-06-10', 25, (SELECT id FROM clubs WHERE name = 'Real Madrid CF')),
  ('Diego Simeone', 'Argentinian', '1970-04-28', 12, (SELECT id FROM clubs WHERE name = 'Atlético de Madrid'));

INSERT INTO players (name, position, jersey_number, date_of_birth, nationality, club_id)
VALUES
  ('Marc-André ter Stegen', 'GOALKEEPER', 1, '1992-04-30', 'German', (SELECT id FROM clubs WHERE name = 'FC Barcelona')),
  ('Ronald Araújo', 'DEFENDER', 4, '1999-03-07', 'Uruguayan', (SELECT id FROM clubs WHERE name = 'FC Barcelona')),
  ('Frenkie de Jong', 'MIDFIELDER', 21, '1997-05-12', 'Dutch', (SELECT id FROM clubs WHERE name = 'FC Barcelona')),
  ('Pedri', 'MIDFIELDER', 8, '2002-11-25', 'Spanish', (SELECT id FROM clubs WHERE name = 'FC Barcelona')),
  ('Robert Lewandowski', 'FORWARD', 9, '1988-08-21', 'Polish', (SELECT id FROM clubs WHERE name = 'FC Barcelona'));

INSERT INTO players (name, position, jersey_number, date_of_birth, nationality, club_id)
VALUES
  ('Thibaut Courtois', 'GOALKEEPER', 1, '1992-05-11', 'Belgian', (SELECT id FROM clubs WHERE name = 'Real Madrid CF')),
  ('Éder Militão', 'DEFENDER', 3, '1998-01-18', 'Brazilian', (SELECT id FROM clubs WHERE name = 'Real Madrid CF')),
  ('Luka Modrić', 'MIDFIELDER', 10, '1985-09-09', 'Croatian', (SELECT id FROM clubs WHERE name = 'Real Madrid CF')),
  ('Jude Bellingham', 'MIDFIELDER', 5, '2003-06-29', 'English', (SELECT id FROM clubs WHERE name = 'Real Madrid CF')),
  ('Vinícius Jr.', 'FORWARD', 7, '2000-07-12', 'Brazilian', (SELECT id FROM clubs WHERE name = 'Real Madrid CF'));

INSERT INTO players (name, position, jersey_number, date_of_birth, nationality, club_id)
VALUES
  ('Jan Oblak', 'GOALKEEPER', 13, '1993-01-07', 'Slovenian', (SELECT id FROM clubs WHERE name = 'Atlético de Madrid')),
  ('José María Giménez', 'DEFENDER', 2, '1995-01-20', 'Uruguayan', (SELECT id FROM clubs WHERE name = 'Atlético de Madrid')),
  ('Koke', 'MIDFIELDER', 6, '1992-01-08', 'Spanish', (SELECT id FROM clubs WHERE name = 'Atlético de Madrid')),
  ('Rodrigo de Paul', 'MIDFIELDER', 5, '1994-05-24', 'Argentinian', (SELECT id FROM clubs WHERE name = 'Atlético de Madrid')),
  ('Álvaro Morata', 'FORWARD', 9, '1992-10-23', 'Spanish', (SELECT id FROM clubs WHERE name = 'Atlético de Madrid'));
