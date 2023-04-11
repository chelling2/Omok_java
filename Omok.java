package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.shape.Ellipse;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;


public class Main extends Application {
	char 차례 = 'B';    // 원래 차례 선택할 수 있게 버튼이용해서 구현하려 했으나 너무 빡쌔서 검은색 고정으로 시작
	int 턴교환 = 1; // 백돌과 흰돌이 돌아가면서 하기 위한 변수이고 2로 나눴을 때 0이면 턴 교환을 쓸 변수다.
	int 연결 = 0; // 돌 하나를 기준으로 4스택까지 연결해서 연달아 5개를 연결하기 위한 변수 

	바둑판[][] 바둑판변수 = new 바둑판[19][19];    //바둑판은 19 * 19의 사이즈이다. 이차원배열 생성 

	Label 레이블 = new Label("검은 돌이 먼저 시작합니다!!");  // 문구 출력을 위한 레이블로 처음엔 검은 돌이 먼저 시작한다는 메세지 출력 

	@Override
	public void start(Stage primaryStage) {

		GridPane gridPane = new GridPane();// 셀의 크기가 고정적이지 않은 그리드팬에 19*19 바둑판 생성 
		for (int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++)
				gridPane.add(바둑판변수[i][j] = new 바둑판(j, i), j, i);  //그리드팬에 가로줄부터 차례로 생성 

		
		레이블.setFont(Font.font("Ariel", FontWeight.LIGHT,FontPosture.REGULAR,30));
		
		// 글꼴은 Ariel 이라는 것을 사용 
		// FontWeight.LIGHT 글자를 가볍게 설정, 이밖에도   FontWeight.BOLD처럼 굵게라던지등등 종류가 여러개 있음
		// FontPosture.ITALIC을 쓰면 글꼴이 이태릭체라 기울여짐. 레귤러는 규칙적인 글꼴.
		
		레이블.setStyle("-fx-text-fill: white; -fx-background-color: black"); 
		//폰트 색상은 흰색  , 백그라운드 컬러를 검은색으로하여 검은색 글자배경에 하얀색 글자가 쓰임
		//레이블.setStyle("-fx-text-fill: yellow; -fx-background-color: black");   ex) 배경 검은색, 폰트 노랑색
		
		// 레이블.setTextFill(Color.web("#0076a3"));  
		// 폰트의 색상을 청주색으로 변경하는 또 다른 메소드. 여기서 인자는 색상표 rgb를 표현한다고 함.


		BorderPane borderPane = new BorderPane();  //BorderPane은 위, 아래, 오른쪽, 왼쪽, 중앙에 컨트롤을 배치함

		borderPane.setCenter(gridPane);      // 보더팬 중앙에 그리드팬으로 만든 바둑판을 인자로 넣는다.
		borderPane.setAlignment(레이블,Pos.CENTER);    // 문구를 가운데 정렬 시켜줌
		borderPane.setTop(레이블);  // 프로그램 끝자락에 문구 출력 set.Bottom을 쓰면 하단에서 출력함

		Scene scene = new Scene(borderPane, 720, 700);    // 씬에 보더팬을 넣고 가로 720 세로 700 사이즈의 씬 생성
		primaryStage.setTitle("그때 그 시절.. 오목");    //창의 이름을 뜻함
		primaryStage.setScene(scene);    // 창에 만든 신을 넣고 
		primaryStage.show();   //창을 보여줌 
	}

	public class 바둑판 extends Pane {   //바둑판의 직접 그린 이미지를 삽입하는 코드 
		char 바둑돌 = ' ';   //바둑돌을 공백으로 설정 해줌 

		public 바둑판(int i, int j) {
			if (i == 0 && j == 0)
				setStyle("-fx-background-image: url(file:///C:/Users/jungh/eclipse-workspace/Class_1123/src/application/left_top.png); -fx-background-position: center");
			else if (i == 18 && j == 0)
				setStyle("-fx-background-image: url(file:///C:/Users/jungh/eclipse-workspace/Class_1123/src/application/right_top.png); -fx-background-position: center");
			else if (i == 0 && j == 18)
				setStyle("-fx-background-image: url(file:///C:/Users/jungh/eclipse-workspace/Class_1123/src/application/left_bottom.png); -fx-background-position: center");
			else if (i == 18 && j == 18)
				setStyle("-fx-background-image: url(file:///C:/Users/jungh/eclipse-workspace/Class_1123/src/application/right_bottom.png); -fx-background-position: center");
			else if (i == 18)
				setStyle("-fx-background-image: url(file:///C:/Users/jungh/eclipse-workspace/Class_1123/src/application/right.png); -fx-background-position: center");
			else if (i == 0)
				setStyle("-fx-background-image: url(file:///C:/Users/jungh/eclipse-workspace/Class_1123/src/application/left.png); -fx-background-position: center");
			else if (j == 18)
				setStyle("-fx-background-image: url(file:///C:/Users/jungh/eclipse-workspace/Class_1123/src/application/bottom.png); -fx-background-position: center");
			else if (j == 0)
				setStyle("-fx-background-image: url(file:///C:/Users/jungh/eclipse-workspace/Class_1123/src/application/top.png); -fx-background-position: center");
			else if ((i == 3 && j == 3) || (i == 3 && j == 9) || (i == 3 && j == 15) ||
					(i == 9 && j == 3) || (i == 9 && j == 9) || (i == 9 && j == 15) ||
					(i == 15 && j == 3) || (i == 15 && j == 9) || (i == 15 && j == 15))
				setStyle("-fx-background-image: url(file:///C:/Users/jungh/eclipse-workspace/Class_1123/src/application/star.png); -fx-background-position: center");
			else
				setStyle("-fx-background-image: url(file:///C:/Users/jungh/eclipse-workspace/Class_1123/src/application/cross.png); -fx-background-position: center");
			this.setPrefSize(700, 700);
			this.setOnMouseClicked(e -> handleMouseClick());
		}

		public char 바둑돌반환() {
			return 바둑돌;
		}

		public void 바둑돌설정(char c) {  //바둑돌의 모양을 생성하고 색상을 넣는 코드 
			바둑돌 = c;   //지역변수 바둑돌을 설정해주고 
 
			if (바둑돌 == 'B') { //바둑돌이 검은색이라면 검은색바둑을 만든다. 
				Ellipse 검정색 = new Ellipse(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2 - 10, this.getHeight() / 2 - 10);
				검정색.centerXProperty().bind(this.widthProperty().divide(2));
				검정색.centerYProperty().bind(this.heightProperty().divide(2));
				검정색.radiusXProperty().bind(this.widthProperty().divide(2).subtract(5));
				검정색.radiusYProperty().bind(this.heightProperty().divide(2).subtract(5));
				검정색.setStroke(Color.BLACK);  // 여기서부터 색상들을 집어넣음 
				검정색.setFill(Color.BLACK);

				getChildren().add(검정색);    // 바둑판에 검정색 바둑돌을 집어넣는 메소드이다.
			}

			else if (바둑돌 == 'W') { //바둑돌이 하얀색이라면 하얀색바둑을 만든다. 
				Ellipse 하얀색 = new Ellipse(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2 - 10, this.getHeight() / 2 - 10);
				하얀색.centerXProperty().bind(this.widthProperty().divide(2));
				하얀색.centerYProperty().bind(this.heightProperty().divide(2));
				하얀색.radiusXProperty().bind(this.widthProperty().divide(2).subtract(5));
				하얀색.radiusYProperty().bind(this.heightProperty().divide(2).subtract(5));
				하얀색.setStroke(Color.WHITE);   // 여기서부터 색상들을 집어넣음 
				하얀색.setFill(Color.WHITE);

				getChildren().add(하얀색);  //바둑판에 하얀색돌을 집어넣는 메소드이다.
			}
		}

		private void handleMouseClick() {  //바둑판에 마우스를 클릭했을 때의 이벤트 !
			if (바둑돌 == ' ' && 차례 != ' ') {     // 바둑돌이 공백이고, 차례가 공백이 아닐시 
				바둑돌설정(차례);    // 이 메소드를 호출하여 바둑돌을 추가하고 옴. 
				턴교환++;       // 턴교환 변수를 하나 증가하고 

				if (승리여부(차례)) {  // 승리여부메소드를 호출하여 true면 종료
					레이블.setText(선수(차례) + "이 승리하였습니다. 축하합니다!");  //선수메소드로가서 차례를 반환하고 문구 출력 
					차례 = ' ';
				}

				else if (가득참()) {   //바둑돌이 가득차면 가득참의 메소드 호출하여 반환값이 true면 종료
					레이블.setText("바둑돌이 가득차서 종료합니다!");
					차례 = ' ';
				}
 
				else {   // 턴교환변수가 짝수이면 차례를 변경하게 만듬 
					if (턴교환 % 2 == 0) {    
						차례 = (차례 == 'B') ? 'W' : 'B';
						턴교환++;      // 여기서 턴교환 안해주면 홀수가 되어서 바둑이 두개씩 놓아지게 됌
					}       
					레이블.setText(선수(차례) + " 차례입니다. ");
				}
			}
		}
	}

	public String 선수(char c) {  //3항연산자로 작성하였으며 파라미터의 값이 검은돌이면 검은돌, 아니면 하얀돌로 변경 
		return (c == 'B') ? "검은돌" : "하얀돌";
	}

	public boolean 승리여부(char stone) {

		// 가로
		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 19; j++) {
				for (int k = 0; k < 4; k++) {
					if (바둑판변수[i+k][j].바둑돌반환() != ' ' && 바둑판변수[i+k][j].바둑돌반환() == 바둑판변수[i+k+1][j].바둑돌반환()) {
						연결++;
					}
					else {
						연결 = 0;
						break;
					}
				}
				if (연결 == 4)
					return true;
			}

		// 세로
		for (int i = 0; i < 19; i++)
			for (int j = 0; j < 15; j++) {
				for (int k = 0; k < 4; k++) {
					if (바둑판변수[i][j+k].바둑돌반환() != ' ' && 바둑판변수[i][j+k].바둑돌반환() == 바둑판변수[i][j+k+1].바둑돌반환()) {
						연결++;
					}
					else {
						연결 = 0;
						break;
					}
				}
				if (연결 == 4)
					return true;
			}

		// 우하향 대각선 
		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++) {
				for (int k = 0; k < 4; k++) {
					if (바둑판변수[i+k][j+k].바둑돌반환() != ' ' && 바둑판변수[i+k][j+k].바둑돌반환() == 바둑판변수[i+k+1][j+k+1].바둑돌반환()) {
						연결++;
					}
					else {
						연결 = 0;
						break;
					}
				}
				if (연결 == 4)
					return true;
			}

		// 좌하향 대각선 
		for (int i = 0; i < 15; i++)
			for (int j = 6; j < 19; j++) {
				for (int k = 0; k < 4; k++) {
					if (바둑판변수[i+k][j-k].바둑돌반환() != ' ' && 바둑판변수[i+k][j-k].바둑돌반환() == 바둑판변수[i+k+1][j-k-1].바둑돌반환()) {
						연결++;
					}
					else {
						연결 = 0;
						break;
					}
				}
				if (연결 == 4)
					return true;
			}      

		return false;
	}

	public boolean 가득참() {    
		for (int i = 0; i < 19; i++)
			for (int j = 0; j < 19; j++)
				if (바둑판변수[i][j].바둑돌반환() == ' ')
					return false;

		return true;
	}

	public static void main(String[] args) {
		launch(args);    //모든 시작의 근원지. 
	}
}