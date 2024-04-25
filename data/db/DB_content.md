**class_level** table
| class_level | class_size |
|-------------|------------|
| 0[^1]:        |            | 
| 1           | 3          |
| 2           | 3          |
| 3           | 25         |
| 4           | 25         |
[^1]:class level 0 is for 5 year olds from Bad Walden to avoid null values in child.class_year and care_offer.youngest_class_level


**employee** table
| employee_number | given_name | surname  |
| --------------- | ---------- | -------- |
| 101             | Anna       | Müller   |
| 102             | Thomas     | Schmidt  |
| 103             | Sarah      | Fischer  |
| 104             | Markus     | Weber    |
| 105             | Laura      | Wagner   |

**child** table
| student_id | class_year | given_name | surname  | age | compulsory_school_attendance | allowed_for_care_offers |
|------------|------------|------------|----------|-----|-----------------------------|-------------------------|
| 1          | 1          | Max        | Schulz   | 7   | True                        | True                    |
| 2          | 4          | Emma       | Schulz   | 9   | True                        | True                    |
| 3          | 3          | Peter      | Schulz   | 8   | True                        | True                    |
| 4          | 3          | Sophie     | Meier    | 7   | False                       | True                    |
| 5          | 1          | Lukas      | Becker   | 7   | False                       | True                    |
| 6          | 2          | Hannah     | Becker   | 7   | False                       | True                    |
| 7          | 3          | Tim        | Hoffmann | 9   | True                        | False                   |
| 8          | 1          | Lisa       | Hoffmann | 6   | True                        | True                    |
| 9          | 4          | Julian     | Klein    | 10  | False                       | True                    |
| 10         | 2          | Mia        | Klein    | 7   | False                       | True                    |
| 11         | 1          | Finn       | Bauer    | 7   | True                        | True                    |
| 12         | 1          | Sophia     | Mauser   | 6   | True                        | True                    |
| 13         | 2          | Elias      | Meier    | 7   | False                       | True                    |
| 14         | 3          | Lea        | Hoffmann | 8   | True                        | False                   |
| 15         | 4          | Ben        | Klein    | 10  | False                       | True                    |
| 16         | 2          | Johanna    | Bauer    | 6   | True                        | True                    |
| 17         | 0          | Nina       | Mauser   | 5   | False                       | True                    |

**parent** table
| parent_id | given_name | surname | city_of_residence |
| --------- | ---------- | ------- | ----------------- |
| 1         | Mannfred   | Schulz  | Bad Walden        |
| 2         | Julia      | Schulz  | Bad Walden        |
| 3         | Andreas    | Meier   | Ludwigshafen      |
| 4         | Katrin     | Meier   | Ludwigshafen      |
| 5         | Simon      | Becker  | Speyer            |
| 6         | Laura      | Becker  | Speyer            |
| 7         | Felix      | Hoffmann| Bad Walden        |
| 8         | Marie      | Hoffmann| Bad Walden        |
| 9         | Martin     | Klein   | Worms             |
| 10        | Christina  | Klein   | Worms             |
| 11        | Thomas     | Bauer   | Bad Walden        |
| 12        | Caroline   | Mauser  | Bad Walden        |

**care_offer** table
| care_offer_id | supervisor_employee_number | oldest_class_level | youngest_class_level | care_offer_name | description | places_available |
| --------------| -------------------------- | ------------------ | ---------------------| --------------- | ----------- | --------------- |
| 1 | 101 | 2 | 1 | Hausaufgabenbetreuung | Hilfe bei Hausaufgaben für Klassenstufe 1 und 2 | 20 |
| 2 | 102 | 4 | 3 | Hort | Betreuung nach der Schule für Klassenstufe 3 und 4 | 15 |
| 3 | 103 | 2 | 0 | Kreativwerkstatt | Kreatives Gestalten für Klassenstufe 1 und 2 | 25 |
| 4 | 104 | 4 | 1 | Sport und Bewegung | Sportliche Aktivitäten für alle Klassenstufen | 20 |
| 5 | 105 | 4 | 4 | Naturerlebnisse | Erkundung der Natur für Klassenstufe 4 | 15 |

**parent_child_assignment** table
| parent_id | child_id |
|-----------|----------|
| 1         | 1        |
| 1         | 2        |
| 1         | 3        |
| 2         | 1        |
| 2         | 2        |
| 2         | 3        |
| 3         | 4        |
| 4         | 4        |
| 4         | 13       |
| 5         | 5        |
| 5         | 6        |
| 6         | 5        |
| 6         | 6        |
| 7         | 7        |
| 7         | 8        |
| 7         | 14       |
| 8         | 7        |
| 8         | 8        |
| 8         | 14       |
| 9         | 9        |
| 9         | 1        |
| 9         | 15       |
| 10        | 9        |
| 10        | 1        |
| 10        | 15       |
| 11        | 11       |
| 11        | 16       |
| 12        | 12       |
| 12        | 17       |

**child_care_offer_assignment** table
| student_id | care_offer_id |
|------------|---------------|
| 1          | 1             |
| 1          | 3             |
| 2          | 2             |
| 2          | 5             |
| 3          | 3[^2]:             | 
| 3          | 4             |
| 4          | 2             |
| 7          | 4             |
| 8          | 1             |
| 9          | 5             |
| 10         | 1             |
| 10         | 3             |
| 10         | 4             |
| 15         | 2             |
| 15         | 5             |
| 17         | 3             |
[^2]:child still in care offer from last year. 
