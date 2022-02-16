# Projet java dao service
DAO : data access object avec une base mysql qui permet de faire le mapping d'utiliseurs avec des roles qui achètent des produits type site E-commerce

Singleton : restriction à une instance

Factory : Instanciation d'objets qu'on ne connait pas lors de l'appel

Facade : Cache la conception des interfaces complexes (ex : DAO)

JUnit : test integration

Log4j : Logger java
# Qu'est-ce qu’une injection SQL ?
Une injection SQL est l'exploitation d'une faille d'une application communiquant avec une base de données. Ainsi l'injection permet d'effectuer des requêtes SQL non prévu par le développeur et peut compromettre la sécurité de l'application.


# Quelle est la différence entre Statement et PreparedStatement ? Quels sont les avantages de l'un par rapport à l'autre ?
Le Statement est une requête SQL ne povant pas être paramétré au niveau des arguments. Le PreparedStatement est précompilé par le système permettant d'enlever les caractères spéciaux et ainsi empécher les injections SQL. Elles sont plus rapide à excécuter par la BDD et permet de réutiliser la requête si nécessaire.
Statement est plus efficace pour les requêtes CREATE, DROP et ALTER (DDL commands) alors que PreparedStatement est plus efficace pour SELECT, UPDATE et INSERT (DML commands).



# Quelle est la différence entre une jointure totale, INNER JOIN, LEFT JOIN et RIGHT JOIN ?
jointure totale : créer une nouvelle table dont chaque ligne est l'association de chaque ligne de la première table avec une ligne de la deuxième dont la condition est respecté dans le ON sinon l'associe avec NULL
(A FULL [OUTER] JOIN B ON A.key = B.key)

`INNER JOIN` : créer une nouvelle table dont chaque ligne est l'association de chaque ligne de la première table avec l'élément de la deuximèe table qui dont la condition est respecté dans le ON
(SELECT * FROM A INNER JOIN B ON A.key = B.key)

`LEFT JOIN`: créer une nouvelle table dont chaque ligne est l'association de la table de gauche à un élément de la table de droite dont la condition du ON est respecté sinon l'associe avec NULL
(SELECT * FROM A LEFT JOIN B ON A.key = B.key)

`RIGHT JOIN`: créer une nouvelle table dont chaque ligne est l'association de la table de droite à un élément de la table de gauche dont la condition du ON est respecté sinon l'associe avec NULL
(SELECT * FROM A RIGHT JOIN B ON A.key = B.key)