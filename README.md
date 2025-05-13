# 📅 todo

일정을 생성하고 댓글과 대댓글을 남길 수 있는 일정 관리 프로그램입니다.

---

## 📌 기능

### ✅ LV1. 일정 CRUD
- 일정 생성 / 조회(전체, 단일) / 수정 / 삭제
- 단일 조회시 댓글, 대댓글까지 함께 조회

### ✅ LV2. 댓글 CRUD
- 특정 일정에 댓글 작성 가능
- 댓글 생성 / 조회 / 수정 / 삭제

### ✅ LV3. 게시물과 댓글 통합 조회
- 일정 목록 조회 시 `댓글 개수(commentCount)` 포함
- 일정 상세 조회 시 작성된 댓글 전체 조회 (작성일 오름차순)

### ✅ LV4. 대댓글 CRUD
- 댓글 하나당 대댓글 하나만 작성 가능
- 대댓글 생성 / 수정 / 삭제 (조회는 일정에서 처리)
- 일정 단일 조회시 댓글 → 대댓글 순으로 조회

---

## 🗂️ 프로젝트 구조

src                                                     
├─ main                                                 
│  ├─ java                                              
│  │  └─ com                                            
│  │     └─ example                                     
│  │        └─ todo                                     
│  │           ├─ common                                
│  │           │  ├─ advice                             
│  │           │  │  └─ GlobalExceptionHandler.java     
│  │           │  ├─ exception                          
│  │           │  │  ├─ base                            
│  │           │  │  │  └─ CustomException.java         
│  │           │  │  └─ enums                           
│  │           │  │     ├─ ErrorCode.java               
│  │           │  │     └─ SuccessCode.java             
│  │           │  └─ response                           
│  │           │     └─ ApiResponseDto.java             
│  │           ├─ controller                            
│  │           │  ├─ CommentController.java             
│  │           │  ├─ ReplyController.java               
│  │           │  └─ ScheduleController.java            
│  │           ├─ dto                                   
│  │           │  ├─ commentDto                         
│  │           │  │  ├─ CommentRequestDto.java          
│  │           │  │  └─ CommentResponseDto.java         
│  │           │  ├─ replyDto                           
│  │           │  │  └─ ReplyResponseDto.java           
│  │           │  └─ scheduleDto                        
│  │           │     ├─ ScheduleDetailResponseDto.java  
│  │           │     ├─ ScheduleRequestDto.java         
│  │           │     └─ ScheduleResponseDto.java        
│  │           ├─ entity                                
│  │           │  ├─ BaseTimeEntity.java                
│  │           │  ├─ Comment.java                       
│  │           │  ├─ Reply.java                         
│  │           │  └─ Schedule.java                      
│  │           ├─ repository                            
│  │           │  ├─ CommentRepository.java             
│  │           │  ├─ ReplyRepository.java               
│  │           │  └─ ScheduleRepository.java            
│  │           ├─ service                               
│  │           │  ├─ comment                            
│  │           │  │  ├─ CommentService.java             
│  │           │  │  └─ CommentServiceImpl.java         
│  │           │  ├─ reply                              
│  │           │  │  ├─ ReplyService.java               
│  │           │  │  └─ ReplyServiceImpl.java           
│  │           │  └─ schedule                           
│  │           │     ├─ ScheduleService.java            
│  │           │     └─ ScheduleServiceImpl.java        
│  │           └─ TodoApplication.java                  
│  └─ resources                                         
│     ├─ static                                         
│     ├─ templates                                      
│     └─ application.properties                         
└─ test                                                 
   └─ java                                              
      └─ com                                            
         └─ example                                     
            └─ todo                                     
               └─ TodoApplicationTests.java         

## API 명세서

![screencapture-notion-so-teamsparta-API-ERD-1e62dc3ef514815db6faf1917244d42a-2025-05-12-20_24_07 (1)](https://github.com/user-attachments/assets/670655c1-2c72-4792-821f-b4543ad81d63)


## ERD 

![image](https://github.com/user-attachments/assets/33552624-d2a0-4ac4-a3d3-5a2cba29ea9c)

## API 동작 캡쳐본

- 일정 생성

![image](https://github.com/user-attachments/assets/f1b89ac1-3520-48e8-8ebe-2fbf5ddad7d0)

- 일정 전체 조회

![image](https://github.com/user-attachments/assets/8cbebac3-3a5f-49c7-87ae-1b1d04423571)

- 일정 상세 조회 (댓글, 대댓글 같이 조회)

![image](https://github.com/user-attachments/assets/f672b5fa-24a8-4fd2-96db-444dd5aac832)
![image](https://github.com/user-attachments/assets/b492f805-a0dc-4fcf-bed5-e7ae130f3797)

- 일정 수정

![image](https://github.com/user-attachments/assets/d5289868-4bc8-4b68-a7c2-3ce1716065cf)

- 댓글 생성

![image](https://github.com/user-attachments/assets/dcd3a34d-435e-425c-9bec-3c9c6df25d57)

- 댓글 조회

![image](https://github.com/user-attachments/assets/38f6925b-a473-41f4-a3ab-8bbcdc37b130)

- 댓글 수정

![image](https://github.com/user-attachments/assets/ba93c567-2c64-406e-b955-8ef9f0c608af)

- 댓글 삭제

![image](https://github.com/user-attachments/assets/3b7b8b23-a701-41b9-945f-15c604f3e355)

- 대댓글 생성

![image](https://github.com/user-attachments/assets/02bce93e-6b14-49b5-97ee-9f213aa5c019)

- 대댓글 수정

![image](https://github.com/user-attachments/assets/428f04f1-8d14-48a8-9db5-9f5880b49149)

- 대댓글 삭제

![image](https://github.com/user-attachments/assets/d5c02f70-6801-4169-84b1-c172d63a7e77)
