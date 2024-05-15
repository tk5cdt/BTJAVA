﻿USE master;
GO
ALTER DATABASE QL_KHACHHANG 
SET SINGLE_USER 
WITH ROLLBACK IMMEDIATE;
GO
DROP DATABASE QL_KHACHHANG;

CREATE DATABASE QL_KHACHHANG
GO
USE QL_KHACHHANG
GO

CREATE TABLE NHOMKHACHHANG(
	MANHOM CHAR(5) NOT NULL,
	TENNHOM NVARCHAR(40),
	CONSTRAINT PK_MN_NKH PRIMARY KEY(MANHOM)
)
GO

CREATE TABLE KHACHHANG(
	MAKH CHAR(5) NOT NULL,
	TENKH NVARCHAR(40),
	NAMSINH INT,
	MANHOM CHAR(5),
	CONSTRAINT PK_MAKH_KhachHang PRIMARY KEY(MAKH),
	CONSTRAINT FK_MANHOM_KhachHang FOREIGN KEY(MANHOM) REFERENCES NHOMKHACHHANG(MANHOM)
)
GO

INSERT INTO NHOMKHACHHANG
VALUES ('N1', N'Triiodothyronine'),
		('N2', N'Trinitrotoluen')

select * from NhomKhachHang

insert into KhachHang(MaKH,TenKH,NamSinh)
values ('KH01',N'Võ Thị Thanh Trúc',2003),
		('KH02',N'Cù Đức Trường',2003),
		('KH03',N'Huỳnh Vũ Chí Thiện',2003),
		('KH04',N'Lê Trương Trọng Tấn',2003),
		('KH05',N'Nguyễn Quốc Thái',2003),
		('KH06',N'Nguyễn Phương Bảo Ngân',2003)


	select * from Khachhang