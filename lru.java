import java.util.*;

public class lru{

    static int pageFaults(int n_pages, int pages[], int frame_size){

        HashSet<Integer> s = new HashSet<>(frame_size);

        HashMap<Integer, Integer> indexes = new HashMap<>();

        int page_faults = 0;

        for(int i = 0; i < n_pages; i++ ){
            if(s.size() < frame_size){
                if(!s.contains(pages[i])){
                    s.add(pages[i]);
                    page_faults++;
                }
                indexes.put(pages[i], i);
            }
            else{

                if(!s.contains(pages[i])){
                    // Logic to find lru and remove it
                    int lru = Integer.MAX_VALUE, val = 0;
                    Iterator<Integer> itr = s.iterator();
                    while(itr.hasNext()){
                        int temp = itr.next();
                        if(indexes.get(temp) < lru){
                            lru = indexes.get(temp);
                            val = temp;
                        }   
                    }
                    s.remove(val);
                    s.add(pages[i]);
                    page_faults++;
                }
                indexes.put(pages[i], i);
            }
        }

        return page_faults;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n_pages, frame_size;

        System.out.print("No of Pages : ");
        n_pages = sc.nextInt();

        int pageString[] = new int[n_pages];
        System.out.println("Enter Page String : ");
        for(int i = 0; i < n_pages; i++){
            pageString[i] = sc.nextInt();
        }

        System.out.print("Enter frame size : ");
        frame_size = sc.nextInt();

        int page_faults = pageFaults(n_pages, pageString, frame_size);

        System.out.println("Page Faults occured : " + page_faults);

    }
}

/*
    Input : 7 0 1 2 0 3 0 4 2 3 0 3 2 
*/