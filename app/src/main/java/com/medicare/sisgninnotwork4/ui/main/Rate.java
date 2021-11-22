package com.medicare.sisgninnotwork4.ui.main;
import com.google.firebase.firestore.Exclude;
public class Rate {

    private String user;



    private int Age;
    private String bloodGroup;
    private boolean disease;
    private String documentId;

    public String getCategory() {
        return category;
    }

    private String category;


    public  Rate(){

    }

    public  int getAge()
    {return Age;
        }
    public  String getBloodGroup()
    {return bloodGroup;
    }
    public  boolean getDisease()
    {return disease;
    }
    public void setDocumentId(String documentId)
    {
        this.documentId=documentId;
    }
    public Rate(String user,int Age,String bloodGroup,boolean disease,String category)
    {
        this.bloodGroup=bloodGroup;
        this.disease=disease;
        this.user=user;
        this.Age=Age;
        this.category=category;
    }
    public String getUser()
    {
        return user;
    }

}
