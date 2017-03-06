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

alter table t_read_circle comment '阅圈基本数据';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   uid                  int not null auto_increment comment '用户id',
   username             	varchar(100) comment '用户名',
   password             varchar(20) comment 'username',
   sex                  int comment '用户性别',
   school               varchar(100) comment '用户学校',
   phonenumber          varchar(30) comment '用户手机号',
   updated_at           date comment '用户最后一次登录时间',
   follower             int comment '粉丝数',
   token                varchar(100) comment '用户token',
   hometown             varchar(100) comment '用户老家',
   face_url             varchar(100) comment '用户头像url',
   signature            	varchar(500) comment '用户签名',
   location             varchar(100) comment '用户地区',
   created_at           date comment '用户注册时间',
   birthday             date comment '用户生日',
   following            int comment '关注数',
   career               	varchar(20) comment '用户职业',
   nickname             varchar(20) comment '用户昵称',
   primary key (uid)
);

alter table t_user comment '用户基本数据';

alter table t_read_circle add constraint FK_Reference_1 foreign key (uid)
      references t_user (uid) on delete restrict on update restrict;

