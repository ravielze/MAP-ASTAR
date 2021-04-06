# Algoritma A*
Algoritma A* adalah algoritma pencarian lintasan terpendek dengan pendekatan heuristik. Dimulai dari simpul awal, A* akan terus mencari lintasan dengan cost terpendek. Pada setiap iterasi, A* akan menghitung cost kira-kira yang akan dibutuhkan dari simpul ke simpul tujuan. 

## Deskripsi Singkat
Pada kasus pencarian lintasan di peta, informasi yang diperoleh adalah koordinat bumi dalam bentuk latitude dan longitude. Fungsi heuristik yang digunakan adalah menghitung jarak antara suatu simpul dengan simpul tujuan. Hal ini dicapai dengan menggunakan formula haversine. Setelah itu, dilakukan algoritma A*.

## Requirements

- Java 11
- Maven

## Usage

### Cara otomatis
Jalankan run.bat untuk Windows, run.sh untuk Linux.

### Cara Manual
Ketik ```mvn clean install```.
Lalu pergi ke ```folder target```, dan akan ada jar yang bisa digunakan disana.
Gunakan command ```java -jar <namajar>.jar```

## Identitas Pembuat
Program ini dibuat oleh [Steven Nataniel](https://github.com/ravielze) dan [Jason Stanley](https://github.com/jasonstanleyyoman) untuk menuntaskan tugas kecil 3 dari mata kuliah IF2211 Strategi Algoritma