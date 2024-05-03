# Grundschule Bad-Walden - Stand 05.04.2024

Dies ist eine Übersicht über die Vorbereitungen, welche wir für das erste Meeting getroffen haben :)

## Allgemeine Übersicht unserer Vorstellungen:
### Personas:

+ Eltern
+ Kinder (reduzierte Berechtigungen im Vergleich zu den Eltern)
+ Angestellte
+ Rektorat (Admin)

### Funktionalitäten:

*  Betreuungsmarktplatz
+ Kalendersystem (unklar in welchem Ausmaß --> Uhrzeitgenau/Taggenau...)
+ Bad Walden Chat (Benachrichtigungssystem)
+ Krankmeldungen (nur ganztags und nicht halbtags)
+ Notenübersicht 
+ Klassenübersicht (nur für Angestellte)
+ Anwesenheitsliste (basierend auf Krankmeldungen)

### Implementierungsumfang
Wir haben uns entschieden die An- und Abmeldung an ein Betreuungsangebot zu implementieren. Damit Eltern ihre Kinde einfach für verschiedene Betreuungsangebote anmelden können. Der komplette Prozess mit allen nötigen Überprfung über die Berechtigung in Care Offers zu gehen oder auch ein Jahrgangstufen check sind implementiert. Alle Änderungen werden in einer Remote DB gespeichert.

## Aufgabenbereiche:

1. Darstellung von Datenbankshema in Entity Relationship Diagramm und Tabellenform
   + **[Issue: Entity Relationship Diagramme](https://github.com/TimeLegend35/ATDIT2_Group_2/issues/2)**
3. Erstellung von Prototypen des UI Designs
   + **[Issue: UI Design](https://github.com/TimeLegend35/ATDIT2_Group_2/issues/10)**
5. Erstellung von Aktivitätsdiagrammen für ausgewählte Prozesse
   + **[Issue: Aktivitätsdiagramme](https://github.com/TimeLegend35/ATDIT2_Group_2/issues/4)**
7. Erstellung von Klassendiagrammen
   + **[Issue: Klassendiagramme](https://github.com/TimeLegend35/ATDIT2_Group_2/issues/1)**
  
## Datenbankschema für unseren Use Case:

### Table: class_levels

| Field        | Type  | Constraint |
|--------------|-------|------------|
| class_level  | INT   | PRIMARY KEY|
| class_size   | INT   |            |

### Table: employees

| Field            | Type        | Constraint |
|------------------|-------------|------------|
| employee_number  | INT         | PRIMARY KEY|
| given_name       | VARCHAR(255)|            |
| surname          | VARCHAR(255)|            |

### Table: children

| Field                  | Type        | Constraint                       |
|------------------------|-------------|----------------------------------|
| student_id             | INT         | PRIMARY KEY                      |
| class_year             | INT         | FOREIGN KEY (class_year) REFERENCES class_levels(class_level) |
| given_name             | VARCHAR(255)|                                  |
| surname                | VARCHAR(255)|                                  |
| age                    | INT         |                                  |
| compulsory_school_attendance| bit   |                                  |
| allowed_for_care_offers    | bit       |                                  |

### Table: parents

| Field            | Type        | Constraint |
|------------------|-------------|------------|
| parent_id        | INT         | PRIMARY KEY|
| given_name       | VARCHAR(255)|            |
| surname          | VARCHAR(255)|            |
| city_of_residence| VARCHAR(255)|            |

### Table: care_offers

| Field                  | Type        | Constraint                                    |
|------------------------|-------------|-----------------------------------------------|
| care_offer_id          | INT         | PRIMARY KEY                                   |
| supervisor_employee_number| INT       | FOREIGN KEY (supervisor_employee_number) REFERENCES employees(employee_number)|
| oldest_class_level     | INT         | FOREIGN KEY (oldest_class_level) REFERENCES class_levels(class_level)|
| youngest_class_level   | INT         | FOREIGN KEY (youngest_class_level) REFERENCES class_levels(class_level)|
| care_offer_name        | VARCHAR(255)|                                               |
| description            | TEXT        |                                               |
| places_available       | INT         |                                               |

### Table: parent_child_assignment

| Field            | Type  | Constraint                            |
|------------------|-------|---------------------------------------|
| parent_id        | INT   | FOREIGN KEY (parent_id) REFERENCES parents(parent_id) |
| child_id         | INT   | FOREIGN KEY (child_id) REFERENCES children(student_id)|
| PRIMARY KEY      |       | (parent_id, child_id)                 |

### Table: child_care_offer_assignment

| Field            | Type  | Constraint                                |
|------------------|-------|-------------------------------------------|
| student_id       | INT   | FOREIGN KEY (student_id) REFERENCES children(student_id)|
| care_offer_id    | INT   | FOREIGN KEY (care_offer_id) REFERENCES care_offers(care_offer_id)|
| PRIMARY KEY      |       | (student_id, care_offer_id)              |


## UI Design Prototypen:

Hier haben wir verschiedene UI Designs für die 3 verschiedenen Personas erstellt.
1. Eltern
2. Kinder
3. Angestellter

Die verschiedenen Prototypen basieren auf den spezifizierten Funktionalitäten, welche nur für ausgewählte Personas gelten

## Aktivitätsdiagramme:

Aufgrund der Fülle der Anforderungen und der Ungewissheit ob alles so bestehen wird, wie geplant, haben wir uns dazu entschieden nur wenige Szenarien/Prozesse darzustellen. 

Beispielhaft: ![grafik](https://raw.githubusercontent.com/TimeLegend35/ATDIT2_Group_2/980348a672eea0636491a68eab5deb2d398c44e5/Aktivit%C3%A4tsdiagramm%20-%2003.04.svg)
