import java.util.Scanner;

class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String username;
        System.out.print("Enter Username: ");
        username = sc.next();
        String password = createValidPassword();
        System.out.println("Yayy! It's a Valid Password!");
        
        sc.close();
    }

    public static String createValidPassword(){
        Scanner sc = new Scanner(System.in);
        boolean hasChar = false, hasNum = false, hasSplCharacters=false;

        while(true){
            System.out.print("Enter a password: ");
            String password = sc.nextLine();
            if(password.contains(" ")){
                System.out.println("Password should not contains Spaces!");
                continue;
            }
            if(password.length()<=8){
                System.out.println("Too short to be a password");
                continue;
            }

            for(int i=0;i<password.length();i++){
                char ch = password.charAt(i);
                if(ch >= 65 && ch <=90){
                    hasChar = true;
                }
                else if(ch>=48 && ch<=57){
                    hasNum = true;
                }
                else if(ch != ' ' && !(ch>=48 && ch<=57) && !(ch >= 65 && ch <=90) && !(ch>=97 && ch<=122)){
                    hasSplCharacters = true;
                }
            }
            if(hasChar && hasNum && hasSplCharacters){
                return password;
            }

            if(!hasChar) System.out.println("Password should contain atleast one Uppercase letter!");
            if(!hasNum) System.out.println("Password should contain atleast one number!");
            if(!hasSplCharacters) System.out.println("Password should contain atleast one special Character!");

        }
    }
}