public class MyVector<T>{
   
    public T[] main_arr;
    public int len;
    public int max_len;    

    public MyVector(){
        this.main_arr = (T[]) new Object[1];
        this.max_len = 1;
        this.len = 0;
    }
    
    public MyVector(int new_len){
        this.main_arr = (T[]) new Object[new_len];
        this.max_len = new_len;
        this.len = 0;
    }
   
    public MyVector(MyVector<T> orig_vector){
        this.main_arr = orig_vector.main_arr;
        this.len = orig_vector.len;
        this.max_len = orig_vector.max_len;
    }
    
    private int new_max(){
        this.max_len = 3*this.len + 10;
        return this.max_len;
    }
    private void extend(){
        T[] new_arr = (T[]) new Object[new_max()];
        for(int i = 0; i < this.len; i++){
            new_arr[i] = this.main_arr[i];
        }
        this.main_arr = new_arr;
    }

    public void append(T data){
        if (this.max_len == this.len){
            extend();
        }
        this.main_arr[len] = data;
        this.len++;
    }

    public void  del_last(){
        try{    
            if (this.len == 1){
                throw new Exception("arr is empty");
            }
            this.len--;
            
        }
        catch (Exception empty){
                System.out.println(empty.getMessage());
        }
    }

    public void del_index(int index){
        try{
                if (index > this.len || index < 0){
                        throw new Exception("index out of range");
                }
                for (int i = index; i < (len - 1); i++ ){
                        this.main_arr[i]=this.main_arr[i+1];
                }
                this.len--;
        }
        catch(Exception e){
                System.out.println(e.getMessage());
        } 
    }

    public void add_index(int index, T data){
        try{
                if (index > this.len || index < 0){
                        throw new Exception("index out of range");
                }
                if (this.len == this.max_len){
                        extend();
                }

                for (int i = len+1; i > index; i--){
                        this.main_arr[i]=this.main_arr[i-1];
                }
                this.main_arr[index]=data;
                this.len++;
        }
        catch(Exception e){
                System.out.println(e.getMessage());
        } 
    }



        
   
    public void  del_val(T val){
        try{    
            for(int i = 0 ; i < len; i++){
                if (this.main_arr[i].equals(val)){
                        del_index(i);
                        return;
                }
            }
            throw new Exception("no such element");
            
            
        }
        catch (Exception no){
                System.out.println(no.getMessage());
        }

    }
    public void clear(){
        this.main_arr = (T[])new Object[1];
        this.max_len = 1;
        this.len = 0;
    }


    
    public int getmax(){
        return this.max_len;
    }
    public int getlen(){
        return this.len;
    }

    public T getByIndex(int index){
        try{
                if (index > this.len || index < 0){
                        throw new Exception("index out of range");
                }
        }
        catch(Exception e){
                System.out.println(e.getMessage());
        }
        return this.main_arr[index];


    }

    public String toString(){
        String str = "";
        for (int i = 0; i < len; i++){
                str = str + this.main_arr[i] + " ";
        }
        return str;
    }

}
