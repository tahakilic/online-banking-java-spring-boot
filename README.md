# Online Banka Sistemi
Bu proje Patika.Dev ile Norma sponsorluğunda düzenlenen Java Spring Boot bootcamp'in bitirme projesidir.

Online banka sistemi projemizin içerisinde müşteri yönetimi, hesap yönetimi, kart yönetimi ve transfer yönetimi fonksiyonları mevcuttur. 
* Müşteri yönetimi fonksiyonu içerisinde; müşteri yaratma, müşteri güncelleme, müşteri silme işlemleri mevcuttur. 
* Hesap yönetimi fonksiyonu içerisinde; hesap yaratma, hesap silme ve atmden oluşturulan banka hesabına para yükleme işlemleri mevcuttur. 
* Kart yönetimi fonksiyonu içerisinde; kart yaratma, kart şifre güncelleme, kart kredi kartı ise limit güncelleme, kart silme, kart kredi kartı ise borç sorgulama, atm'den karta para yükleme ve kart ile alışveris yapma işlemleri mevcuttur. 
* Transfer yönetimi fonksiyonu içerisinde; hesaplar arası para transferi, karttan hesaba para transferi, hesaptan karta para transferi işlemleri mevcuttur.

## Getting Started

* Dili: Java
* Version: Java 17
* Freamwork: Spring Boot

### Prerequisites

* Bilgisayarınızda Java 17 yüklü olması gerekmektedir. Yüklü değilse  [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) adresinden indirebilirsiniz.

* Projede; `mvn clean install` yapabilmemiz için yüklü olan java 17 bilgisayarımıza tanımlamamız gerekmektedir. tanımlı değil ise [Java Path Tanımlama](https://java.com/tr/download/help/path_tr.html) adresinden path tanımlama ile adımları takip ederek path tanımlayabilirsiniz.


### Installing

* IDE üzerinden veya git üzerinden github reposunu yerel bilgisayarınıza clone ediniz.

```
git clone https://github.com/tahakilic/online-banking-java-spring-boot.git
```

* Intellij yardımıyla projeyi açınız.
* Projeyi çalıştırmadan önce `mvn clean install` yapınız.
* `mvn clean install` sorunsuz çalıştıktan sonra projeyi run edebiliriz.

## APIS
### Müşteri APIS
#### Müşteri Oluşturma
POST **`localhost:8080/api/customers`** <br>
Body: 
```
{
  "account": {
    "accountNumber": 2100000000000001,
    "accountType": "DEPOSIT_ACCOUNT",  //DEPOSIT_ACCOUNT – CHECKING_ACCOUNT
    "bankCode": "00001",
    "branchCode": "0001",
    "currencyCode":"TRY",		//TRY – EUR - USD
    "iban": "TR000000000000000000000001"
  },

  "contactInformation": {
    "primaryEmail": "taha@gmail.com",
    "primaryPhoneNumber": "05375202556",
    "secondaryEmail": "taha34@gmail.com",
    "secondaryPhoneNumber": "05345568985"
  },
  "customerAddress": {
    "addressType": "HOUSE",  //HOUSE - JOB
    "city": "Istanbul",
    "country": "Turkey",
    "district": "Beykoz",
    "description": "Kavacık Mahallesi No:12"  
  },
  "gender": "MALE",  //MALE – FEMALE - OTHER
  "identityNumber": 10000000001,
  "lastName": "Demir",
  "middleName": "Ali",
  "name": "Taha",
  "birthDay": "1994-03-31"
}
```

#### Müşteri Güncelleme
PUT **`localhost:8080/api/customers`** <br>
Body: 
```
{
    "id":1,
    "name": "Taha",
    "middleName":"Ali",
    "lastName": "Demir",
    "identityNumber": 10000000001,
    "gender": "MALE",  //MALE – FEMALE - OTHER
    "birthDay": "1994-03-31",
    "contactInformation": {
            "primaryEmail": "taha@hotmail.com",
            "primaryPhoneNumber": "05361074861",
            "secondaryEmail": "tahayedek34@hotmail.com",
            "secondaryPhoneNumber": "05448000832"
  }
}
```
#### Müşteri Okuma
GET **`localhost:8080/api/customers/{customerId}`**   ->   `{customerId}` yazan yere müşterinin id'si yazılmalıdır.

#### Müşteri Silme
Delete **`localhost:8080/api/customers/{customerId}`**   ->   `{customerId}` yazan yere müşterinin id'si yazılmalıdır.

#### Müşteri Adres Ekleme
POST **`localhost:8080/api/customers/{customerId}/addresses`**  ->   `{customerId}` yazan yere müşterinin id'si yazılmalıdır.<br> 
Body: 
```
{
    "country":"Turkey",
    "city":"Istanbul",
    "district":"Kadikoy",
    "addressType":"JOB",  //HOUSE - JOB
    "description":"Fener Kalamış Cad. No:63A "
}
```

#### Müşteri Adres Güncelleme
PUT **`localhost:8080/api/customers/addresses/{customerAddressId}`**  ->   `{customerAddressId}` yazan yere adresin id'si yazılmalıdır.<br> 
Body: 
```
{
    "country":"Turkey",
    "city":"Istanbul",
    "district":"Kadikoy",
    "addressType":"JOB",  //HOUSE - JOB
    "description":"Fener Kalamış Cad. No:63A "
}
```
#### Adres Okuma
GET **`localhost:8080/api/customers/addresses/{customerAddressId}`**  ->   `{customerAddressId}` yazan yere adresin id'si yazılmalıdır. <br>

#### Müşterinin Tüm Adreslerini Okuma
GET **`localhost:8080/api/customers/{customerId}/addresses`**  ->    `{customerId}` yazan yere müşterinin id'si yazılmalıdır. <br>

#### Müşteri Adress Silme
DELETE **`localhost:8080/api/customers/addresses/{customerAddressId}`**  ->   `{customerAddressId}` yazan yere adresin id'si yazılmalıdır. 
<br>
<br>

### Hesap APIS
#### Hesap Oluşturma
POST **`localhost:8080/api/accounts/customers/{customerId}`**  ->   `{customerId}` yazan yere müşterinin id'si yazılmalıdır. <br>
Body: 
```
{
    "accountNumber": 2100000000000001,
    "accountType": "DEPOSIT_ACCOUNT",  //DEPOSIT_ACCOUNT – CHECKING_ACCOUNT
    "bankCode": "00001",
    "branchCode": "0001",
    "currencyCode":"TRY",
    "iban": "TR000000000000000000000001"
}
```
#### Hesap Okuma
GET **`localhost:8080/api/accounts/{accountId}`**  ->   `{accountId}` yazan yere hesabın id'si yazılmalıdır. <br>

#### Müşterinin Tüm Hesaplarını Okuma
GET **`localhost:8080/api/accounts/customers/{customerId}`**  ->    `{customerId}` yazan yere müşterinin id'si yazılmalıdır. <br>

#### Hesap Silme
DELETE **`localhost:8080/api/accounts/{accountId}`**  ->   `{accountId}` yazan yere hesabın id'si yazılmalıdır. <br>

#### ATM'den Hesaba Para Yükleme
PUT **`localhost:8080/api/accounts?iban=TR000000000000000000000001&amount=100`**  <br>   `iban` değeri paranın gönderileceği hesabın ibanı yazılmalıdır. `amount` değeri paranın miktarı yazılmalıdır.<br>

<br>

### Kart APIS
#### Kart Oluşturma
POST **`localhost:8080/api/cards/customers/{customerId}`**  ->   `{customerId}` yazan yere müşterinin id'si yazılmalıdır. <br>
Body: 
```
{
  "cardNumber": 2200000000000001,
  "cardSecurityNumber": "123",
  "cardExpirationDate":"12/25",
  "cardPassword": "123321",
  "cardLimit":10000,
  "currentLimit":1000,
  "cardType": "CREDIT_CARD"  //CREDIT_CARD – BANK_CARD
}
```
* Kart kredi kartı ise `cardLimit` eklenmelidir. Kart banka kartı ise `cardLimit` çıkarılmalıdır. 

#### Kart Limit Güncelleme
PUT **`localhost:8080/api/cards/{cardId}/limits`**  ->   `{cardId}` yazan yere kartın id'si yazılmalıdır. <br>
Body: 
```
{
    "cardLimit": 10000,
    "currentLimit":200
}
```

#### Kart Şifre Güncelleme
PUT **`localhost:8080/api/cards/{cardId}/passwords`**  ->   `{cardId}` yazan yere kartın id'si yazılmalıdır. <br>
Body: 
```
{
    "password":"123321"
}
```
#### Kart Okuma
GET **`localhost:8080/api/cards/{cardId}`**  ->   `{cardId}` yazan yere kartın id'si yazılmalıdır. <br>

#### Müşterinin Tüm Kartlarını Okuma
GET **`localhost:8080/api/cards/customers/{customerId}`**  ->    `{customerId}` yazan yere müşterinin id'si yazılmalıdır. <br>

#### Kart Silme
DELETE **`localhost:8080/api/cards/{cardId}`**  ->   `{cardId}` yazan yere kartın id'si yazılmalıdır. <br>

#### Kredi Kartı Borç Sorgulama
GET **`localhost:8080/api/cards?cardNumber=2200000000000001`**  ->   `cardNumber` değerine kartın numarası yazılmalıdır. <br>

#### ATM'den Karta Para Yükleme
PUT **`localhost:8080/api/cards?cardNumber=2200000000000001&amount=500`**  ->   `cardNumber` değerine kartın numarası yazılmalıdır. `amount` değeri paranın miktarı yazılmalıdır. <br>

#### Kart ile Alışveriş Yapma
PUT **`localhost:8080/api/cards/shopping`**  
Body: 
```
{
    "amount":1,
    "cardNumber":2200000000000001,
    "cardSecurityNumber":"136",
    "cardExpirationDate":"10/25",
    "cardPassword":"123321"
}
```
<br><br>

### Transfer APIS
#### Hesaplar Arası Para Transferi
POST **`localhost:8080/api/transfers?fromIban=TR000000000000000000000001&toIban=TR000000000000000000000002&amount=100`**  
Body: 
```
{
    "description":"Diğer ödemeler"
}
```
#### Hesaptan Karta Para Transferi
POST **`localhost:8080/api/transfers/to/cards?fromAccountIban=TR000000000000000000000001&toCardNumber=2200000000000001&amount=100`**  

#### Karttan Hesaba Para Transferi
POST **`localhost:8080/api/transfers/to/accounts?fromCardNumber=2200000000000001&toAccountIban=TR000000000000000000000001&amount=20`**  
