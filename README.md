# SoftPOS
Android Developer Challenge

# Description
SoftPOS is an application that assist shop owner manage his shop payments using his mobile phone 
to accept contacless smartcard using NFC technology.

# Logo
![Image of Yaktocat](https://svgshare.com/i/Nrp.svg)

# Demo GIF
![GIF](https://github.com/Solymanx7/SoftPOS/blob/master/SoftPOS.gif)

# Demo 
[SoftPOS Video - Google Drive](https://drive.google.com/file/d/1KAAlaoD-PjrmCnXF3-N4FHWOJUY-UIPb/view?usp=sharing)


# Challenges
1. Make text field that accept inputs from most right by mlutiplying each new digit by 10 and still have cursor to edit where you want, select or delete all. I challenged my self by making this feature same as in video but enhanced it by leaving to the user the freedom of editing for best user exprience. This part took me some time to handle corner cases at first i was thinking about using bit shift using bitwise operators but i found that i can't apply it since i have decimal numbers
2. Working on API level 14, I wanted to challenge my self so that my app get the best comptability and can run neraly into any phone. The problems i faced was deperecated functions that i had to find altertnat soultions , and finally i solved them.
3. Working with NFC is really challenging since the google documentation for me wasn't enough and was a little vague , so i searched over 30+ web page resources and tried different piece of codes to understand the mechanism and how NFC works. I also searched for what CreditCard chip made of and its internal design , Offessts & Addresses , APDPU Command but found that i may require some time to read specific data at hardware level so i decided to get enough with only tag returned by NFC intent.
4. RoomDB wasn't a real challenge but it was first time for me to use it and i had some fun learning and applying new concept.

## How I started ?
1. First, before creating project, I had to make sure what is the minimum SDK version NFC EMV can support because it's the app main functionality, I found out it started from API level 9 , 10 , 14 and API level 14 has many features to use so I decided to use it.

2. Second, I started with UI part:
    1. Deciding Color Platte for banking/payment apps after some research I found most banks tends to use mix between green and blue so I decided to get mix of these one: https://colorhunt.co/palette/189745 , https://color-hex.org/color/0c352e
    2. Deciding Font type, found that **source code pro** is sharp and suitable for numeric data.

3. Third, after getting thoughts about how my app will look like and how transation will go i decided to apply file structure and folders organizing

4. Fourth, I started to code, debug, repeat :)

## Ideas came into my head for future updates !
1. Considered that the person who owns the app has vision disability so we can add TTS and STT features using google speech api 
2. Barcode scanner in addition to text inputs to automate the insertion values of some products
3. Fingerprint/Face scan before each payment process




