/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     09/09/2020 14:46:17                          */
/*==============================================================*/


alter table "Cand_Comp"
   drop constraint FK_ASSOCIAT_ASSOCIATI_CANDIDA3;

alter table "Cand_Comp"
   drop constraint FK_CAND_COM_ASSOCIATI_COMPETEN;

alter table "Cand_Lang"
   drop constraint FK_CAND_LAN_ASSOCIATI_CANDIDAT;

alter table "Cand_Lang"
   drop constraint FK_CAND_LAN_ASSOCIATI_LANGUES;

alter table "Cand_Loi"
   drop constraint FK_ASSOCIAT_ASSOCIATI_CANDIDA2;

alter table "Cand_Loi"
   drop constraint FK_CAND_LOI_ASSOCIATI_LOISIRS;

alter table "Candidat"
   drop constraint FK_CANDIDAT_ASSOCIATI_SITUATIO;

alter table "ExperienceProfessionnelle"
   drop constraint FK_EXPERIEN_ASSOCIATI_CANDIDAT;

alter table "ExperienceProfessionnelle"
   drop constraint FK_EXPERIEN_ASSOCIATI_VILLE;

alter table "FormationEtDiplomes"
   drop constraint FK_FORMATIO_ASSOCIATI_CANDIDAT;

alter table "FormationEtDiplomes"
   drop constraint FK_FORMATIO_ASSOCIATI_VILLE;

alter table "Langue_Niveau"
   drop constraint FK_LANGUE_N_NIVLANG_NIVEAU;

alter table "Langue_Niveau"
   drop constraint FK_LANGUE_N_ASSOCIATI_LANGUES;

drop index ASSOCIATION3_FK2;

drop index ASSOCIATION3_FK;

drop table "Cand_Comp" cascade constraints;

drop index ASSOCIATION5_FK2;

drop index ASSOCIATION5_FK;

drop table "Cand_Lang" cascade constraints;

drop index ASSOCIATION4_FK2;

drop index ASSOCIATION4_FK;

drop table "Cand_Loi" cascade constraints;

drop index ASSOCIATION7_FK;

drop table "Candidat" cascade constraints;

drop table "Competences" cascade constraints;

drop index ASSOCIATION9_FK;

drop index ASSOCIATION2_FK;

drop table "ExperienceProfessionnelle" cascade constraints;

drop index ASSOCIATION8_FK;

drop index ASSOCIATION1_FK;

drop table "FormationEtDiplomes" cascade constraints;

drop table "Langue_Niveau" cascade constraints;

drop table "Langues" cascade constraints;

drop table "Loisirs" cascade constraints;

drop table "Niveau" cascade constraints;

drop table "SituationFamiliale" cascade constraints;

drop table "Ville" cascade constraints;

/*==============================================================*/
/* Table: "Cand_Comp"                                           */
/*==============================================================*/
create table "Cand_Comp" 
(
   "idCandidat"         INTEGER              not null,
   "idCompetence"       INTEGER              not null,
   constraint PK_CAND_COMP primary key ("idCandidat", "idCompetence")
);

/*==============================================================*/
/* Index: ASSOCIATION3_FK                                       */
/*==============================================================*/
create index ASSOCIATION3_FK on "Cand_Comp" (
   "idCandidat" ASC
);

/*==============================================================*/
/* Index: ASSOCIATION3_FK2                                      */
/*==============================================================*/
create index ASSOCIATION3_FK2 on "Cand_Comp" (
   "idCompetence" ASC
);

/*==============================================================*/
/* Table: "Cand_Lang"                                           */
/*==============================================================*/
create table "Cand_Lang" 
(
   "idCandidat"         INTEGER              not null,
   "idLangue"           INTEGER              not null,
   constraint PK_CAND_LANG primary key ("idCandidat", "idLangue")
);

/*==============================================================*/
/* Index: ASSOCIATION5_FK                                       */
/*==============================================================*/
create index ASSOCIATION5_FK on "Cand_Lang" (
   "idCandidat" ASC
);

/*==============================================================*/
/* Index: ASSOCIATION5_FK2                                      */
/*==============================================================*/
create index ASSOCIATION5_FK2 on "Cand_Lang" (
   "idLangue" ASC
);

/*==============================================================*/
/* Table: "Cand_Loi"                                            */
/*==============================================================*/
create table "Cand_Loi" 
(
   "idCandidat"         INTEGER              not null,
   "idLoisir"           INTEGER              not null,
   constraint PK_CAND_LOI primary key ("idCandidat", "idLoisir")
);

/*==============================================================*/
/* Index: ASSOCIATION4_FK                                       */
/*==============================================================*/
create index ASSOCIATION4_FK on "Cand_Loi" (
   "idCandidat" ASC
);

/*==============================================================*/
/* Index: ASSOCIATION4_FK2                                      */
/*==============================================================*/
create index ASSOCIATION4_FK2 on "Cand_Loi" (
   "idLoisir" ASC
);

/*==============================================================*/
/* Table: "Candidat"                                            */
/*==============================================================*/
create table "Candidat" 
(
   "idCandidat"         INTEGER              not null,
   "idSituationF"       INTEGER              not null,
   "nom"                VARCHAR2(254),
   "prenom"             VARCHAR2(254),
   "dtNaissance"        DATE,
   "telephone"          VARCHAR2(254),
   "email"              VARCHAR2(254),
   "photo"              VARCHAR2(254),
   "adresse"            VARCHAR2(254),
   constraint PK_CANDIDAT primary key ("idCandidat")
);

/*==============================================================*/
/* Index: ASSOCIATION7_FK                                       */
/*==============================================================*/
create index ASSOCIATION7_FK on "Candidat" (
   "idSituationF" ASC
);

/*==============================================================*/
/* Table: "Competences"                                         */
/*==============================================================*/
create table "Competences" 
(
   "idCompetence"       INTEGER              not null,
   "intituleCmp"        VARCHAR2(254),
   constraint PK_COMPETENCES primary key ("idCompetence")
);

/*==============================================================*/
/* Table: "ExperienceProfessionnelle"                           */
/*==============================================================*/
create table "ExperienceProfessionnelle" 
(
   "idExperience"       INTEGER              not null,
   "idVille"            INTEGER              not null,
   "idCandidat"         INTEGER              not null,
   "poste"              VARCHAR2(254),
   "etabPoste"          VARCHAR2(254),
   "periode"            INTEGER,
   constraint PK_EXPERIENCEPROFESSIONNELLE primary key ("idExperience")
);

/*==============================================================*/
/* Index: ASSOCIATION2_FK                                       */
/*==============================================================*/
create index ASSOCIATION2_FK on "ExperienceProfessionnelle" (
   "idCandidat" ASC
);

/*==============================================================*/
/* Index: ASSOCIATION9_FK                                       */
/*==============================================================*/
create index ASSOCIATION9_FK on "ExperienceProfessionnelle" (
   "idVille" ASC
);

/*==============================================================*/
/* Table: "FormationEtDiplomes"                                 */
/*==============================================================*/
create table "FormationEtDiplomes" 
(
   "idDiplome"          INTEGER              not null,
   "idCandidat"         INTEGER              not null,
   "idVille"            INTEGER              not null,
   "diplome"            VARCHAR2(254),
   "etablissementDiplome" VARCHAR2(254),
   "anneeObt"           INTEGER,
   constraint PK_FORMATIONETDIPLOMES primary key ("idDiplome")
);

/*==============================================================*/
/* Index: ASSOCIATION1_FK                                       */
/*==============================================================*/
create index ASSOCIATION1_FK on "FormationEtDiplomes" (
   "idCandidat" ASC
);

/*==============================================================*/
/* Index: ASSOCIATION8_FK                                       */
/*==============================================================*/
create index ASSOCIATION8_FK on "FormationEtDiplomes" (
   "idVille" ASC
);

/*==============================================================*/
/* Table: "Langue_Niveau"                                       */
/*==============================================================*/
create table "Langue_Niveau" 
(
   "idLangue"           INTEGER              not null,
   "idNiveau"           INTEGER              not null,
   constraint PK_LANGUE_NIVEAU primary key ("idLangue", "idNiveau")
);

/*==============================================================*/
/* Table: "Langues"                                             */
/*==============================================================*/
create table "Langues" 
(
   "idLangue"           INTEGER              not null,
   "intituleLangue"     VARCHAR2(254),
   constraint PK_LANGUES primary key ("idLangue")
);

/*==============================================================*/
/* Table: "Loisirs"                                             */
/*==============================================================*/
create table "Loisirs" 
(
   "idLoisir"           INTEGER              not null,
   "intittuleLoisirs"   VARCHAR2(254),
   constraint PK_LOISIRS primary key ("idLoisir")
);

/*==============================================================*/
/* Table: "Niveau"                                              */
/*==============================================================*/
create table "Niveau" 
(
   "idNiveau"           INTEGER              not null,
   "intituleNiveau"     INTEGER,
   constraint PK_NIVEAU primary key ("idNiveau")
);

/*==============================================================*/
/* Table: "SituationFamiliale"                                  */
/*==============================================================*/
create table "SituationFamiliale" 
(
   "idSituationF"       INTEGER              not null,
   "intituleSituation"  INTEGER,
   constraint PK_SITUATIONFAMILIALE primary key ("idSituationF")
);

/*==============================================================*/
/* Table: "Ville"                                               */
/*==============================================================*/
create table "Ville" 
(
   "idVille"            INTEGER              not null,
   "intituleVille"      INTEGER,
   constraint PK_VILLE primary key ("idVille")
);

alter table "Cand_Comp"
   add constraint FK_ASSOCIAT_ASSOCIATI_CANDIDA3 foreign key ("idCandidat")
      references "Candidat" ("idCandidat");

alter table "Cand_Comp"
   add constraint FK_CAND_COM_ASSOCIATI_COMPETEN foreign key ("idCompetence")
      references "Competences" ("idCompetence");

alter table "Cand_Lang"
   add constraint FK_CAND_LAN_ASSOCIATI_CANDIDAT foreign key ("idCandidat")
      references "Candidat" ("idCandidat");

alter table "Cand_Lang"
   add constraint FK_CAND_LAN_ASSOCIATI_LANGUES foreign key ("idLangue")
      references "Langues" ("idLangue");

alter table "Cand_Loi"
   add constraint FK_ASSOCIAT_ASSOCIATI_CANDIDA2 foreign key ("idCandidat")
      references "Candidat" ("idCandidat");

alter table "Cand_Loi"
   add constraint FK_CAND_LOI_ASSOCIATI_LOISIRS foreign key ("idLoisir")
      references "Loisirs" ("idLoisir");

alter table "Candidat"
   add constraint FK_CANDIDAT_ASSOCIATI_SITUATIO foreign key ("idSituationF")
      references "SituationFamiliale" ("idSituationF");

alter table "ExperienceProfessionnelle"
   add constraint FK_EXPERIEN_ASSOCIATI_CANDIDAT foreign key ("idCandidat")
      references "Candidat" ("idCandidat");

alter table "ExperienceProfessionnelle"
   add constraint FK_EXPERIEN_ASSOCIATI_VILLE foreign key ("idVille")
      references "Ville" ("idVille");

alter table "FormationEtDiplomes"
   add constraint FK_FORMATIO_ASSOCIATI_CANDIDAT foreign key ("idCandidat")
      references "Candidat" ("idCandidat");

alter table "FormationEtDiplomes"
   add constraint FK_FORMATIO_ASSOCIATI_VILLE foreign key ("idVille")
      references "Ville" ("idVille");

alter table "Langue_Niveau"
   add constraint FK_LANGUE_N_NIVLANG_NIVEAU foreign key ("idLangue")
      references "Niveau" ("idNiveau");

alter table "Langue_Niveau"
   add constraint FK_LANGUE_N_ASSOCIATI_LANGUES foreign key ("idLangue")
      references "Langues" ("idLangue");

