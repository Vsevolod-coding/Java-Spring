package com.example;

import com.google.gson.Gson;

public class App
{
    public static void main( String[] args )
    {
        Gson gson = new Gson();
        Person person = new Person("John", "Doe", 44);

        // Сериализация
        String json = gson.toJson(person);
        System.out.println("Сериализованный JSON: " + json);

        // Десериализация
        String jsonString = "{\"firstName\":\"Alice\",\"lastName\":\"Smith\",\"age\":25}";
        Person deserializedPerson = gson.fromJson(jsonString, Person.class);
        System.out.println("\nДесериализованный объект: " + deserializedPerson);
    }
}
