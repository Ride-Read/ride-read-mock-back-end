/*==============================================================*/
/* Database name:  qiyue                                        */
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/3/6 14:08:22                            */
/*==============================================================*/


drop database if exists qiyue;

/*==============================================================*/
/* Database: qiyue                                              */
/*==============================================================*/
create database qiyue;

use qiyue;

/*==============================================================*/
/* Table: t_read_circle                                         */
/*==============================================================*/
create table t_read_circle
(
   mid                  int not null auto_increment,
   created_at           date,
   comment              varchar(1000),
   uid                  int,
   cover                varchar(1000),
   pictures             varchar(1000),
   thumbs_up            varchar(1000),
   video                varchar(100),
   thumbs               varchar(1000),
   msg                  varchar(1000),
   primary key (mid)
);

alter table t_read_circle comment '��Ȧ��������';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   uid                  int not null auto_increment comment '�û�id',
   username             	varchar(100) comment '�û���',
   password             varchar(20) comment 'username',
   sex                  int comment '�û��Ա�',
   school               varchar(100) comment '�û�ѧУ',
   phonenumber          varchar(30) comment '�û��ֻ���',
   updated_at           date comment '�û����һ�ε�¼ʱ��',
   follower             int comment '��˿��',
   token                varchar(100) comment '�û�token',
   hometown             varchar(100) comment '�û��ϼ�',
   face_url             varchar(100) comment '�û�ͷ��url',
   signature            	varchar(500) comment '�û�ǩ��',
   location             varchar(100) comment '�û�����',
   created_at           date comment '�û�ע��ʱ��',
   birthday             date comment '�û�����',
   following            int comment '��ע��',
   career               	varchar(20) comment '�û�ְҵ',
   nickname             varchar(20) comment '�û��ǳ�',
   primary key (uid)
);

alter table t_user comment '�û���������';

alter table t_read_circle add constraint FK_Reference_1 foreign key (uid)
      references t_user (uid) on delete restrict on update restrict;

