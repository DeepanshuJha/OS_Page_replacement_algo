import java.util.*;

public class optimal{

    static int search(int key, int frame[], int frame_size){
        int x = 0;
        for(int i = 0; i < frame_size; i++){
            if(key == frame[i]){
                x = 1;
            }
        }
        return x;
    }

    static int predict(int pages[], int frame[], int n_pages, int frame_size, int start){
        int pos = Integer.MIN_VALUE;
        int res = -1;
        for(int i = 0; i < frame_size; i++){
            int j;
            for(j = start; j < n_pages; j++){
                if(pages[j] == frame[i]){
                    if(pos < j){
                        pos = j;
                        res = i;
                    }
                    break;
                }
            }
            if(j == n_pages){
                return i;
            }
        }
        return (res == -1) ? 0 : res;
    }

    static void optimalPage(int n_pages, int pages[], int frame_size){
        
        int frame[] = new int[frame_size];
        int f_sz = 0; // to count frame size

        int hit = 0;
        for(int i = 0; i < n_pages; i++){
            
            //Page Found : HIT
            if(search(pages[i], frame, frame_size) == 1){
                hit++;
                continue;
            }

            // Page not Found : MISS
            if(f_sz < frame_size){
                frame[f_sz] = pages[i];
                f_sz++;
            }
            else{
                int pos = predict(pages, frame, n_pages, frame_size, i + 1);
                frame[pos] = pages[i];
            }
        }
        
        System.out.println("Page HIT   : " + hit);
        System.out.println("Page FAULT : " + (n_pages - hit));
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n_pages, frame_size;

        System.out.print("No. of pages : ");
        n_pages = sc.nextInt();

        int pages[] = new int[n_pages];
        System.out.println("Enter page string : ");

        for(int i = 0; i < n_pages; i++){
            pages[i] = sc.nextInt();
        }

        System.out.print("Enter Frame Size : ");
        frame_size = sc.nextInt();

        optimalPage(n_pages, pages, frame_size);
    }
}

/*
    Input : 7 0 1 2 0 3 0 4 2 3 0 3 2 
*/