create database QL_DIACDDVD
go
use QL_DIACDDVD
go

create table CDDVDCollection(
	Ma char(5) not null,
	TieuDe nvarchar(50),
	LoaiDia char(10),
	NamXuatBan int,
	constraint PK_CDDVDCollection primary key(Ma)
)
go

insert into CDDVDCollection (Ma,TieuDe, LoaiDia,NamXuatBan)
values ('SP1',N'CD Xuân Ca','CD',2003),
		('SP2',N'DVD Học Kế Toán','DVD',2001),
		('SP3',N'DVD Học Tiếng Anh','DVD',2002),
		('SP4',N'CD Hải Ngoại','CD',2013),
		('SP5',N'CD Nhạc Tết','CD',2021),
		('SP6',N'DVD Phim Hài','DVD',2020),
		('SP7',N'CD VinaHouse','CD',2024)


select * from CDDVDCollection
Go

create proc ShowListCDDVD
as
	select * from CDDVDCollection
go

exec ShowListCDDVD

create proc ShowListCDDVDByLoai
@LoaiDia nvarchar(50)
as
	select * from CDDVDCollection where LoaiDia = @LoaiDia
go

exec ShowListCDDVDByLoai @LoaiDia = 'CD'
go

create proc ShowListCDDVDByNamXuatBan
@NamXB int
as
	select * from CDDVDCollection where NamXuatBan = @NamXB
go

exec ShowListCDDVDByNamXuatBan @NamXB = 2020

create proc ShowListCDDVDByNamXBAndLoai
@NamXB int,@LoaiDia nvarchar(50)
as
	select * from CDDVDCollection where NamXuatBan = @NamXB and LoaiDia = @LoaiDia 
go
exec ShowListCDDVDByNamXBAndLoai @NamXB = 2020, @LoaiDia = 'DVD'

