
import java.util.Scanner;
public class radar {

	public static void main(String[] args) {
		node radar_array[];
		int x;
		int i;
		Scanner scan = new Scanner(System.in);
		int my_num = Integer.parseInt(scan.nextLine());
		int tempid;
		int tempy;
		int tempx;
		
		radar_array = new node[my_num];
		int n = my_num;
		for(x=0;x<my_num;x++) {
			String line = scan.nextLine();
			String[] line_array = line.split(" ");
			tempid = Integer.parseInt(line_array[0]);
			tempx = Integer.parseInt(line_array[1]);
			tempy = Integer.parseInt(line_array[2]);
			node a_node = new node(tempx, tempy, 0, tempid);
			radar_array[x] = a_node;
			
		}
		
		mergeSortx(radar_array, n);
		
		get_load_factors(0, n-1, radar_array);

		mergeSortid(radar_array, radar_array.length);
		print_array(radar_array);
	}
	
	public static void get_load_factors(int start, int end, node[] array) {
		int mid = (int) Math.floor((start+end)/2);
		if (start == end) {
			array[start].load_factor = 0;
		}
		else {
			get_load_factors(start, mid, array);
			get_load_factors(mid+1, end, array);
			node[] left_array = create_array(start, mid, array);
			node[] right_array = create_array(mid+1, end, array);
			mergeSorty(right_array,right_array.length);
			mergeSorty(left_array, left_array.length);
			int i;
			for (i=0;i<right_array.length;i++) {
				int the_count = my_binary_search(left_array, right_array[i].y);
				if (the_count != -1) {
					right_array[i].load_factor = right_array[i].load_factor + 1 + the_count;
					//right_array[i].load_factor = right_array[i].load_factor + 1 + left_array[the_count].load_factor;
				}
			}
			return;
		}
	}
	
	public static int my_binary_search(node[] array, int key) {
		int start = 0;
		int end = array.length-1;
		int mid;
		while(start<end) {
			mid = (int)Math.floor((end+start)/2);
			if (array[mid+1].y <= key) {
				start = mid+1;
			}
			else {
				end = mid;
			}
		}
		if (start == end && end == 0) {
			if (array[0].y <= key) {
				return 0;
			}
			else {
				return -1;
			}
		}
		else {
			return start;
		}
	}
	
	public static node[] create_array(int start, int end, node[] array) {
		int size = (end-start)+1;
		node[] new_array = new node[size];
		int x;
		int count = 0;
		for(x=start;x<end+1;x++) {
			new_array[count] = array[x];
			count = count+1;
		}
		
		
		return new_array;
		
	}
	
	public static void mergeSortx(node[] a, int n) {
	    if (n < 2) {
	        return;
	    }
	    int mid = n / 2;
	    node[] l = new node[mid];
	    node[] r = new node[n - mid];
	 
	    for (int i = 0; i < mid; i++) {
	        l[i] = a[i];
	    }
	    for (int i = mid; i < n; i++) {
	        r[i - mid] = a[i];
	    }
	    mergeSortx(l, mid);
	    mergeSortx(r, n - mid);
	 
	    mergex(a, l, r, mid, n - mid);
	    
	}
	public static void mergex(
	  node[] a, node[] l, node[] r, int left, int right) {
	  
	    int i = 0, j = 0, k = 0;
	    while (i < left && j < right) {
	        if (l[i].x < r[j].x) {
	            a[k++] = l[i++];
	        }
	        else {
	            a[k++] = r[j++];
	        }
	    }
	    while (i < left) {
	        a[k++] = l[i++];
	    }
	    while (j < right) {
	        a[k++] = r[j++];
	    }
	}
	
	public static void mergeSorty(node[] a, int n) {
	    if (n < 2) {
	        return;
	    }
	    int mid = n / 2;
	    node[] l = new node[mid];
	    node[] r = new node[n - mid];
	 
	    for (int i = 0; i < mid; i++) {
	        l[i] = a[i];
	    }
	    for (int i = mid; i < n; i++) {
	        r[i - mid] = a[i];
	    }
	    mergeSorty(l, mid);
	    mergeSorty(r, n - mid);
	 
	    mergey(a, l, r, mid, n - mid);
	}
	public static void mergey(
	  node[] a, node[] l, node[] r, int left, int right) {
	  
	    int i = 0, j = 0, k = 0;
	    while (i < left && j < right) {
	        if (l[i].y < r[j].y) {
	            a[k++] = l[i++];
	        }
	        else {
	            a[k++] = r[j++];
	        }
	    }
	    while (i < left) {
	        a[k++] = l[i++];
	    }
	    while (j < right) {
	        a[k++] = r[j++];
	    }
	}
	
	public static void mergeSortid(node[] a, int n) {
	    if (n < 2) {
	        return;
	    }
	    int mid = n / 2;
	    node[] l = new node[mid];
	    node[] r = new node[n - mid];
	 
	    for (int i = 0; i < mid; i++) {
	        l[i] = a[i];
	    }
	    for (int i = mid; i < n; i++) {
	        r[i - mid] = a[i];
	    }
	    mergeSortid(l, mid);
	    mergeSortid(r, n - mid);
	 
	    mergeid(a, l, r, mid, n - mid);
	}
	public static void mergeid(
	  node[] a, node[] l, node[] r, int left, int right) {
	  
	    int i = 0, j = 0, k = 0;
	    while (i < left && j < right) {
	        if (l[i].id < r[j].id) {
	            a[k++] = l[i++];
	        }
	        else {
	            a[k++] = r[j++];
	        }
	    }
	    while (i < left) {
	        a[k++] = l[i++];
	    }
	    while (j < right) {
	        a[k++] = r[j++];
	    }
	}
	
	public static void print_array(node[] array) {
		int x;
		for(x=0;x<array.length;x++) {
			System.out.println(array[x].id + " :"+ array[x].load_factor);
		}
	}
	  
}


class node {
	int x;
	int y;
	int load_factor;
	int id;
	
	node(int x_val, int y_val, int lf, int an_id){
		this.x = x_val;
		this.y = y_val;
		this.load_factor = lf;
		this.id = an_id;
	}
}
