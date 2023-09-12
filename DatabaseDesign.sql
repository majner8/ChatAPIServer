use chatAPP;
drop table if exists user;
create table User(
email varchar(150),country_preflix varchar(3), phone varchar(13),useruuid int auto_increment primary key, 
Sername varchar(50),lastname varchar(50),
birthday date,
is_user_active boolean default false,
version int );

ALTER TABLE User
ADD UNIQUE INDEX unique_country_phone (country_preflix, phone);
 drop table  if exists UserPassword;
create table UserPassword(useruuid int primary key,
password varchar(70));

drop table if exists deviceuser;
create table deviceuser(
deviceuuid varchar(36), validTokenUUID varchar(10),userUUID int not null,
lastActivity datetime default now(),is_user_current_active boolean,
primary key(userUUID,deviceuuid));

drop table if exists device_ip_adress;
create table device_ip_adress(
deviceuuid varchar(36),userUUID int not null,
ipadress varchar(45),
lastActivity datetime default now(),
primary key(userUUID,deviceuuid));


drop trigger if exists deviceUserInsert;

drop table if exists SecurityMonitoring;

create table SecurityMonitoring(
ip_adress varchar(16),action_time datetime default now(), email varchar(150),country_preflix varchar(3), phone varchar(13),
attempt_sucesfull boolean,id int primary key auto_increment);

drop table if exists Message;
create table Message
(message_text varchar(400),sender_uuid int,
message_uuid varchar(20),-- generated on client side
delivery_time datetime,does_message_text boolean,
answer_other_message_uuid int,
chat_order bigint,
chat_uuid bigint,
primary key(chat_uuid,chat_order));

ALTER TABLE Message
ADD UNIQUE INDEX message_index (sender_uuid, message_uuid,chat_uuid);

drop trigger if exists messageOrder;
DELIMITER //
CREATE TRIGGER messageOrder
BEFORE INSERT ON Message
FOR EACH ROW
BEGIN
    SET NEW.chat_order = (SELECT COALESCE(MAX(chat_order), 0) + 1 FROM Message 
    WHERE chat_uuid = NEW.chat_uuid);
END;
//
DELIMITER ;


drop trigger if exists generate_message_uuid;
DELIMITER //
CREATE TRIGGER generate_message_uuid
BEFORE INSERT ON Message
FOR EACH ROW
BEGIN
    SET NEW.message_uuid = (SELECT COALESCE(MAX(message_uuid), 0) + 1 FROM Message 
    WHERE chat_uuid = NEW.chat_uuid);
END;
//
DELIMITER ;

drop table if exists chat;
create table chat(chatuuid int primary key auto_increment ,chat_name varchar(20));

drop table if exists chatMember;
create table chatMember(
useruid int, name_in_chat varchar(20),administrator boolean,
chatuuid int,
primary key(useruid,chatuuid)
);