package com.example.sodevs;

import com.example.sodevs.models.HotelModel;

import java.util.ArrayList;
import java.util.List;

public class Hotels {
    private List<HotelModel> hotels;
    private static Hotels singleInstance;

    private Hotels() {
        this.hotels = new ArrayList<>();
    }

    public static synchronized Hotels getInstance() {
        if (singleInstance == null) {
            singleInstance = new Hotels();
        }
        return singleInstance;
    }

    public List<HotelModel> getHotels() {
        return hotels;
    }

    public void addHotel(String name, String location, String description) {
        this.hotels.add(new HotelModel(name, location, description));
    }

    public void createHotels() {
        HotelModel hotelSinaia1 = new HotelModel("Hotel Regal", "Sinaia", "Located in Sinaia, Hotel Regal offers spacious and elegantly furnished apartments with picturesque views and spa baths. Free Wi-Fi as well as free parking is provided.  The hotel bar serves fresh coffee, tea and various beverages in a welcoming atmosphere.  The Regal has an outdoor terrace with a barbecue area.  This is our guests' favourite part of Sinaia, according to independent reviews.  This property also has one of the best-rated locations in Sinaia! Guests are happier about it compared to other properties in the area.");
        hotelSinaia1.addRoom("Single", 1, 120);
        hotelSinaia1.addRoom("Single", 1, 120);
        hotelSinaia1.addRoom("Double", 2, 210);
        hotelSinaia1.addRoom("Double", 2, 210);
        hotelSinaia1.addRoom("Triple", 3, 320);
        hotelSinaia1.addRoom("Triple", 3, 320);
        hotelSinaia1.addRoom("Apartment", 4, 440);
        hotels.add(hotelSinaia1);

        HotelModel hotelSinaia2 = new HotelModel("Hotel Bastion", "Sinaia", "Hotel Bastion is situated on the royal domain of the Peles Castle. The hotel includes a restaurant serving fine Romanian and international cuisine, a bar, a terrace and secured parking.\n" +
                "\tOnce a royal summer residence, Hotel Bastion offers you elegant en-suite rooms, each with a different style. They are equipped with a flat-screen satellite TV, a minibar and free wired internet connection.\n" +
                "\tThe Sinaia Cable Car is 3 km away and the Sinaia Train Station is 1.5 km away.\n" +
                " \n" +
                "\tThis is our guests' favourite part of Sinaia, according to independent reviews.\n" +
                "\tThis property also has one of the best-rated locations in Sinaia! Guests are happier about it compared to other properties in the area.\n");
        hotelSinaia2.addRoom("Single", 3, 211);
        hotelSinaia2.addRoom("Double", 2, 285);
        hotelSinaia2.addRoom("Triple", 5, 342);
        hotels.add(hotelSinaia2);

        HotelModel hotelSinaia3 = new HotelModel("Hotel Cumpatu", "Sinaia", "Hotel Cumpatu is located in Sinaia, 3 km away from the castles of Peles and Pelisor, the cable car and the gondola, leading to the Dorului Valley, Carp Valley and Sun Valley Ski Slopes. It offers views of the Bucegi Mountains or the forests, free public WiFi and free parking.\n" +
                " \n" +
                "\tAll rooms come with flat-screen cable TV, a minibar, and a safe. The private bathrooms have bathrobes, slippers, hairdryers and toiletries.\n" +
                "\tHotel Cumpatu also has an adventure park on its premises, offering a zipline, tree climbing, a easy-level tree track, and guests of the property benefit from a discount of the entry fee. Children can use the indoor play room or play in the outdoors with small pets. Massages are available at an extra cost.\n" +
                "\tThe restaurant serves Romanian as well as international cuisine. Guests can start their day with a buffet breakfast every morning.\n" +
                "\tA transfer from Sinaia's Train Station, which is 4 km away, is available at extra costs. The beginners' ski slopes in Valea Dorului and the advanced ski slopes in the Carp and Soarelui Valleys are approximately 10 km away from the Cumpatu. Brasov is 41 km away.\n");
        hotelSinaia3.addRoom("Single", 1, 208);
        hotelSinaia3.addRoom("Double", 2, 296);
        hotelSinaia3.addRoom("Double", 2, 342);
        hotelSinaia3.addRoom("Double", 2, 425);
        hotels.add(hotelSinaia3);

        HotelModel hotelPredeal1 = new HotelModel("Hotel Belvedere", "Predeal", "Hotel Belvedere is set in Predeal resort and enjoys a location surrounded by Bucegi, Postăvarul and Piatra Mare mountains. It is provided with a terrace with impressive views, spa facilities, and free of charge Wi-Fi in public areas.\n" +
                "\tThe stylish rooms feature a balcony, a minibar and cable TV. All rooms have private bathrooms equipped with a shower and free toiletries.\n" +
                "\tYou can choose from the international dishes served at Hotel Belvedere’s restaurant accompanied by a wine or relax on the terrace while enjoying a panoramic view over the resort.\n" +
                "\tMassages and sauna are available on site at an additional cost. The resort’s cable car is 2.5 km away and Clăbucet Ski Slope can be reached within 5 minutes’ drive. Predeal Train Station is 2.5 km away from the property.\n" +
                " \n" +
                "\tThis is our guests' favourite part of Predeal, according to independent reviews.\n");
        hotelPredeal1.addRoom("Single", 1, 115);
        hotelPredeal1.addRoom("Single", 1, 115);
        hotelPredeal1.addRoom("Double", 2, 190);
        hotelPredeal1.addRoom("Triple", 3, 300);
        hotelPredeal1.addRoom("Triple", 3, 300);
        hotelPredeal1.addRoom("Apartment", 4, 390);
        hotels.add(hotelPredeal1);

        HotelModel hotelPredeal2 = new HotelModel("Hotel Orizont", "Predeal", "Situated on the edge of Predeal, Hotel Orizont offers a spa area and air-conditioned rooms with free Wi-Fi and a flat-screen TV. A restaurant serving international cuisine and another restaurant where you can sample Romanian dishes are featured on site.\n" +
                "\tThe Orizont’s rooms are fitted with modern furniture. They feature a bathroom with free toiletries and a hairdryer. Some come with a seating area or a sofa.\n" +
                "\tThe spa area consists of an indoor swimming pool, a hot tub, a sauna and guests enjoy complimentary access. Massages are available at a surcharge. Hotel Orizont is surrounded by a lush garden where children can play on a playground.\n" +
                "\tA bar is a further feature of the hotel. Free private parking is possible on site and the train station is 1 km away.\n" +
                "\tPeleș Castle is 13 km from the property and the closest ski slopes are 1.5 km away. The well-known Bran Castle can be reached within 35 minutes by car.\n" +
                " \n" +
                "\tThis is our guests' favourite part of Predeal, according to independent reviews.\n");
        hotelPredeal2.addRoom("Single", 1, 329);
        hotelPredeal2.addRoom("Double", 2, 362);
        hotelPredeal2.addRoom("Double", 2, 423);
        hotelPredeal2.addRoom("Double", 2, 517);
        hotelPredeal2.addRoom("Apartament", 4, 1417);
        hotels.add(hotelPredeal2);

        HotelModel hotelPredeal3 = new HotelModel("Hotel Rozmarin", "Predeal", "Hotel Rozmarin is located in Predeal close to the Orasenec Stadium. It offers nicely refurbished accommodation with free Wi-Fi internet access.\n" +
                "\tThere is free parking on site. The Rozmarin features a breakfast lounge, a terrace, a fitness area and a hot tub. There is also a sauna and sun beds.\n" +
                "\tThe restaurant serves breakfast and traditional Romanian cuisine accompanied by wines from the hotel's own wine cellar.\n" +
                " \n" +
                "\tThis is our guests' favourite part of Predeal, according to independent reviews.\n");
        hotelPredeal3.addRoom("Single", 1, 115);
        hotelPredeal3.addRoom("Single", 1, 177);
        hotelPredeal3.addRoom("Double", 2, 198);
        hotelPredeal3.addRoom("Double", 2, 298);
        hotelPredeal3.addRoom("Triple", 3, 389);
        hotelPredeal3.addRoom("Apartament", 4, 428);
        hotels.add(hotelPredeal3);

        HotelModel hotelConstanta1 = new HotelModel("Hotel Phoenicia Royal", "Constanta", "Phoenicia Royal Hotel is set on the beachfront of Mamaia and offers a private beach. A seasonal swimming pool is available.\n" +
                "\tEvery room at this hotel is air conditioned and comes with a flat-screen TV with cable channels. Certain rooms include views of the sea or lake. For your comfort, you will find bathrobes, slippers and free toiletries in the private bathrooms.\n" +
                "\tThe staff of this hotel is at guests' disposal around-the-clock at the front desk. Baggage storage and daily housekeeping are also provided.\n" +
                "\tPhoenicia Royal Hotel offers a wide range of seasonal services, including a children's playground, an indoor play area, and shuttle services. The seasonal services, available during from May to September, include a swimming pool, a kids' pool, and a water park, as well as a sauna, a hammam, and a playground. Other seasonal services include the a la carte restaurant, the minimarket and the room service.\n");
        hotelConstanta1.addRoom("Single", 1, 598);
        hotelConstanta1.addRoom("Single", 1, 719);
        hotelConstanta1.addRoom("Double", 2, 1104);
        hotelConstanta1.addRoom("Apartament", 4, 1308);
        hotels.add(hotelConstanta1);

        HotelModel hotelConstanta2 = new HotelModel("Hotel Opera", "Constanta", "Offering a heated outdoor pool and a private beach, Hotel Opera Mamaia is situated in Mamaia, a few steps from the main night clubs. Free WiFi is available throughout the property.\n" +
                "\tThe air-conditioned rooms include a flat-screen TV with cable channels, a minibar, a desk and a private bathroom. For your comfort, you will find bathrobes, a hairdryer, slippers and free toiletries. You can keep your valuables in the safety deposit box.\n" +
                "\tGuests have at their disposal a 24-hour front desk, and a concierge service. In case you want to catch some sun after the check-out, free luggage storage is available. Upon request, the property can help you with airport transfers, at an extra fee. If you arrive by car, this hotel offers a free private parking.\n" +
                "\tYou can enjoy a meal at the à la carte restaurant, or a drink at the bar. If you want to arrange an event during your stay, this property also offers meeting and banquet facilities.\n");
        hotelConstanta2.addRoom("Single", 1, 382);
        hotelConstanta2.addRoom("Single", 1, 461);
        hotelConstanta2.addRoom("Double", 2, 1093);
        hotelConstanta2.addRoom("Apartament", 4, 1312);
        hotels.add(hotelConstanta2);

        HotelModel hotelConstanta3 = new HotelModel("Hotel Manor", "Constanta", "Hotel Manor features air-conditioned rooms with satellite flat-screen TV in the Mamaia Nord district of Mamaia. Among the facilities of this property are a restaurant, a 24-hour front desk and room service, along with free WiFi. Guests can make use of a bar.\n" +
                " \n" +
                "\tAll rooms in the hotel are fitted with a kettle. The rooms include a private bathroom with a shower, a hair dryer and free toiletries.\n" +
                "\tA buffet breakfast is available each morning at Hotel Manor.\n" +
                "\tThe accommodation offers a terrace.\n" +
                "\tAqua Magic is 10 km from Hotel Manor, while Kudos Beach is 5 km from the property. The nearest airport is Mihail Kogălniceanu International, 25 km from the hotel, and the property offers a paid airport shuttle service.\n" +
                " \n" +
                "\tMamaia Nord is a great choice for travellers interested in clubbing, beaches for children and entertainment.\n");
        hotelConstanta3.addRoom("Single", 1, 301);
        hotelConstanta3.addRoom("Double", 2, 432);
        hotelConstanta3.addRoom("Triple", 3, 517);
        hotelConstanta3.addRoom("Apartament", 4, 658);
        hotels.add(hotelConstanta3);

        HotelModel hotelSibiu1 = new HotelModel("Continental Forum", "Sibiu", "Set in a historic building in the centre of Sibiu, the 4-star Continental Forum offers elegant rooms with free WiFi. Guests can enjoy international and typical Balkan cuisine at the restaurant.\n" +
                "\tThe hotel's restaurant serves a daily buffet breakfast, a la carte lunch and dinner at the hotel`s restaurant Balkan Bistro.\n" +
                "\tDelicious pastry and confectionery specialities are offered at the TeKaffe, along with some tea, coffee and cappuccino.\n" +
                "\tContinental Forum Sibiu is only a 3-minute walk from the Brukenthal Museum and Piata Mare.\n" +
                " \n" +
                "\tThis is our guests' favourite part of Sibiu, according to independent reviews.\n");
        hotelSibiu1.addRoom("Single", 1, 322);
        hotelSibiu1.addRoom("Single", 1, 346);
        hotelSibiu1.addRoom("Double", 2, 396);
        hotelSibiu1.addRoom("Triple", 3, 586);
        hotelSibiu1.addRoom("Triple", 3, 490);
        hotels.add(hotelSibiu1);

        HotelModel hotelSibiu2 = new HotelModel("Da Vinci Residence", "Sibiu", "Set 1 km from the Old Town and 200 m from the Business Centre of Sibiu, Da Vinci Residence is housed in a modern designer building. Free WiFi is available in public areas and free private parking is possible on site.\n" +
                "\tThe rooms at the Da Vinci Residence feature a flat-screen cable TV, a minibar, a safety deposit box and a private bathroom with shower, slippers and free toiletries. Views of the mountains or the city are provided.\n" +
                "\tGuests can rent bicycles at the property.\n" +
                "\tThe Little Square and the Bridge of Lies are 1.9 km away and the Sibiu Train Station is 3 km away. The guest house is 100 m from the Mihai Viteazu Bus Stop.\n");
        hotelSibiu2.addRoom("Single", 1, 145);
        hotelSibiu2.addRoom("Double", 2, 208);
        hotels.add(hotelSibiu2);

        HotelModel hotelSibiu3 = new HotelModel("The Council", "Sibiu", "Set in a former 14th-century city hall, this central property is part of the Sibiu Medieval Fortress and offers classically furnished accommodation right next to the Council Tower, within walking distance from the Small Square, the Bridge of Lies and the Large Square.\n" +
                "\tAir conditioning and free WiFi are standard facilities. Various restaurants and bars are reachable within a distance of 100 m.\n" +
                "\tThe Council is a pleasant and elegant guest house, which is an ideal starting point to explore the city. After reception hours, guests arriving late have the option of self check-in.\n" +
                "\tThe accommodaton is within 200 m from the Bruckenthal Museum and the Evangelical Cathedral. The Sibiu Train Station is 700 m away.\n" +
                " \n" +
                "\tThis is our guests' favourite part of Sibiu, according to independent reviews.\n");
        hotelSibiu3.addRoom("Single", 1, 255);
        hotelSibiu3.addRoom("Double", 2, 336);
        hotelSibiu3.addRoom("Apartament", 2, 396);
        hotels.add(hotelSibiu3);
    }
}
