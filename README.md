# UML - 스타크래프트를 객체지향 방식 프로그래밍 구현
<img src="https://postfiles.pstatic.net/MjAyMjA1MzBfMTQ5/MDAxNjUzODM3MjU2MTQ0.73pvZ6nQwr7My2JxBd_Krx07kkzUuPj8z9v3AyRbGJMg.p0hBIDuY_laxrLWqRulj6jwZ9yMNFY8oBTV6Hifdw_8g.PNG.forget980/image.png?type=w580" width="20%" height="20%" title="px(픽셀) 크기 설정" alt="RubberDuck"></img>
### 목표
* #### 스타클래프트의 종족 구현(Zerg Protoss Terran 세가지 종족이 있다)
* #### 종족의 유닛 밎 건물 관계
### 설계
* 종족의 공통된 특징을 인터페이스를 통해 구현
* 캡술화를 통해 객체간의 간섭을 최소화.
* 인터페이스를 통한 다형성 확대.
* 상속보다 표함을 이용하여 상속으로 밸생할 수 있는 문제를 최소화.
* 발생할 수 있는 문제를 예외처리하여 사용자에게 적극적으로 알린다.
* 역할을 유추할 수 있도록 해더 작성.
* 완성된 UML를 바탕으로 소스코드 작성.
![Alt text](/star/star_.drawio_.png)

[UML_원본](https://mail.naver.com/read/image/?mailSN=15436&attachIndex=2&contentType=image/png&offset=1415&size=720432&mimeSN=1653835620.734712.57257.46848&org=1&u=forget980)

[source_code_package](https://github.com/1000004/PERSONAL-PROJECTS/tree/UML/star/src/kr/ac/star)
