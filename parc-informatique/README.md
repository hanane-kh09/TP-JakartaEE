# Parc Informatique - Application de Gestion de Parc d'Entreprise

Cette application permet de gérer l'inventaire du matériel informatique d'une entreprise et le suivi des affectations aux employés. Elle offre à la fois une interface utilisateur d'administration complète et réactive (Thymeleaf/Bootstrap 5) et des points d'accès API REST d'origine.

---

## 🛠️ Stack Technique

* **Langage & Frameowrk** : Java 17, Spring Boot 3.2.0
* **Accès aux données** : Spring Data JPA, Hibernate, MySQL Dialect
* **Moteur de Template & Design** : Thymeleaf, Bootstrap 5 (CDN), Font Awesome 6 (CDN)
* **Utilitaires** : Lombok, Jakarta Validation
* **Build tool** : Maven

---

## 📁 Structure des Packages

```text
ma.projet.parcinformatique
  ├── dto/          (Objets de transfert pour l'IHM)
  ├── entity/       (Entités JPA : Materiel, Employe, Affectation)
  ├── enums/        (Énumérations métier)
  ├── repository/   (Interfaces JpaRepository)
  ├── service/      (Logique applicative)
  └── controller/   (Points d'accès REST)
        └── web/    (Contrôleurs MVC pour l'IHM web)
```

---

## 🖥️ Fonctionnalités & Pages Web (IHM)

1. **Tableau de Bord (`/` ou `/dashboard`)** :
   * Indicateurs clés (Total Matériels, Total Employés, Affectations actives, Taux d'utilisation).
   * Raccourcis vers l'activité récente (Matériels et affectations récents).
2. **Matériels (`/materiels`)** :
   * Liste, recherche multicritères par référence ou marque.
   * Ajout, modification, consultation de fiche individuelle avec historique d'affectations.
   * Suppression sécurisée par modale de confirmation.
3. **Employés (`/employes`)** :
   * Liste des profils, recherche par nom ou service.
   * Création et mise à jour des informations avec validation d'email unique.
   * Détails d'employé avec liste de ses matériels affectés.
4. **Affectations (`/affectations`)** :
   * Suivi des attributions, filtrage par statut et département.
   * Création d'attribution avec listes déroulantes de choix matériels et employés.
   * Clôture en un clic avec retour matériel daté au jour même.

---

## 📡 Endpoints API REST (Rétrocompatibilité)

L'ensemble des endpoints REST d'origine restent disponibles sous le préfixe `/api` :

### Matériels (`/api/materiels`)
* `GET /` : Liste complète ou filtrée par paramètres (ex: `?type=PC&etat=OK`).
* `GET /{id}` : Détails d'un matériel.
* `POST /` : Création (contrôle de référence unique).
* `PUT /{id}` : Mise à jour (patch partiel des attributs).
* `DELETE /{id}` : Suppression.
* `GET /stats/pannes` : Nombre de pannes regroupées par type de matériel.
* `GET /stats/par-type` : Total des matériels regroupés par type.

### Employés (`/api/employes`)
* `GET /` : Liste complète ou filtrée par service (ex: `?service=Informatique`).
* `GET /{id}` : Fiche de l'employé.
* `POST /` : Enregistrement (contrôle d'email unique).
* `PUT /{id}` : Mise à jour.
* `DELETE /{id}` : Suppression.

### Affectations (`/api/affectations`)
* `GET /` : Liste complète ou filtrée par statut et service (ex: `?statut=ACTIVE&service=RH`).
* `GET /{id}` : Détails d'une affectation.
* `POST /affecter` : Effectuer une affectation (paramètres: `materielId`, `employeId`, `dateDebut`).
* `PUT /{id}/cloturer` : Clôturer une affectation active.
* `GET /employe/{employeId}` : Liste des affectations d'un employé.
* `GET /materiel/{materielId}` : Liste des affectations d'un matériel.
* `GET /stats/utilisation` : Statistiques de taux d'utilisation globale.

---

## 🚀 Lancement local

### Prérequis
* Un serveur de base de données **MySQL** actif sur le port `3306`.
* L'utilisateur de base de données configuré comme `root` avec un mot de passe vide.

### Étapes
1. Clonez ou ouvrez le projet dans votre répertoire de travail.
2. Démarrez votre serveur MySQL. L'application créera automatiquement la base de données `parc_informatique` si elle n'existe pas lors du premier lancement (grâce au paramètre `createDatabaseIfNotExist=true`).
3. Compilez le projet :
   ```bash
   mvn clean compile
   ```
4. Démarrez l'application Spring Boot :
   ```bash
   mvn spring-boot:run
   ```
5. Accédez à l'application web via votre navigateur à l'adresse : [http://localhost:8080](http://localhost:8080).

*Note : Lors du premier démarrage, un jeu de données de test (6 matériels, 4 employés et 4 affectations) est inséré automatiquement en base.*
