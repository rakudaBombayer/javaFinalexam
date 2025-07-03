package Final;

import java.util.Scanner;

public class Sort {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		
		//↑ヒットしたデータを取るコードを書く
		
		
		Scanner scanner = new Scanner(System.in);
        String input;
        int number = 0;
        boolean isValid = false;

        // 有効な入力が得られるまで繰り返す
        while (!isValid) {
            System.out.print("表示件数を入力： ");
            input = scanner.nextLine();

            if (validateInput(input)) {
                try {
                    number = Integer.parseInt(input);
                    isValid = true; // 有効な場合ループ終了
                } catch (NumberFormatException e) {
                	//このケースを考える必要はないかも↓（あまりに発生しずらいケースなので）
                    System.out.println("無効な数値です。整数値を入力してください。");
                }
            } else {
                System.out.println("整数値を入力してください。");
            }
        }

        System.out.println("入力された有効な数値: " + number);
        //return number;(表示件数のデータを返す)
        
     // 仮データを生成して表示
        displayDummyData(number);
        
        //↓検索した結果が0件の場合は、「該当なし」と表示します。

    }
		
	
	  // データを表示するメソッド
    	public static void displayDummyData(int count) {
    		System.out.println("=== 仮データ ===");
    		for (int i = 1; i <= count; i++) {
    			System.out.println("データ " + i + ": サンプル内容");
    		}
    	}

	
		//ヴァリデーションをかけた。
    	public static boolean validateInput(String input) {
    		return input.matches("\\d+"); // 数値のみ
    	}
		
		

}
