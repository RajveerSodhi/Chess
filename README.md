# In-Console Chess

___


## Introduction

This is the second in-Console chess program I have created after [this](https://github.com/RajveerSodhi/Class-12-Chess).

I first created a chess program using Python in my senior year of high school to challenge myself to solve complex problems using my beginner understanding of the language and coding in general. I created this program a year later after studying Java at UBC in my first year. This was a much better and more organized program than my first attempt due to my increased experience with programming and knowledge of more concepts such as OOP. I wrote this code to not only track my progress as a programmer, which is visible in the efficiency and organization in this code compared to that of my first program but also to test whether I completely grasped all the concepts I was taught in class.

I am keen to see how I can advance my program more once I learn further programming concepts.

The program is fully functioning as-is, however, please do keep checking the currently known issues in the Issues section to stay up to date about the program's failures and drawbacks.

___


## Walkthrough

On loading the program, the text output in the console displays a chess board made with UNICODE characters.

<img width="355" alt="image" src="https://github.com/RajveerSodhi/Chess/assets/65150031/a450b13b-cb53-4e83-8930-9025d51382fd">

_(Note that in this image the colors of the chess pieces are inverted due to a dark display theme of the IDE. The set of pieces on the bottom of the board are white.)_

Each square on the board has a coordinate such as 1A, 2B, and so on until 8H. The program asks you to enter the current coordinate of the piece you want to move and then the coordinate of the destination square.

It prompts the first player (controlling white pieces) to play. If either of the coordinates entered is invalid, the program shows an error message accordingly.

<img width="358" alt="image" src="https://github.com/RajveerSodhi/Chess/assets/65150031/89bd40f8-c7f8-40ac-973c-ea0dbc75fcb4">

If the move is valid, it updates the board visualization with the move and asks the opposing player to make a move after.

<img width="385" alt="image" src="https://github.com/RajveerSodhi/Chess/assets/65150031/1326cd63-b01c-4a2e-84bd-9d22d1079075">

Similarly, the program allows for a full-fledged chess game to be played on the computer. It contains code for the restricted movement of all chess pieces and also includes programming for features such as castling, pawn upgrades, check, etc.

Typing in "rules" at any point displays all the rules to use the program and classical chess.

<img width="1596" alt="image" src="https://github.com/RajveerSodhi/Chess/assets/65150031/1a5f6fc3-99e7-4300-bd1b-f438a497731e">

___


## Future Development Ideas

This program is far from complete. I want to add several features over time, including some of the following:

- A GUI implementation of the chessboard
- Automatic detection of a checkmate
- In-game clock
- Single Player Mode against the computer
- A mode which shows suggested moves which help people learn the game
- Generation of an exportable transcript of each game and a GUI to access all stored transcripts on the computer, along with a leaderboard based on them

___


## Updates and Fixes

**July 2023**
- Added game rules to a .txt file
- Updated README.md

**May 2023**
- Fixed display issues
- Updated README.md

**July 2022**
- Uploaded Project Files to GitHub
