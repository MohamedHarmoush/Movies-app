package harmoush.com.movies.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import harmoush.com.movies.data.models.Movie

class DummyData {

    private val dummyResponse = """ [
        {
            "vote_count": 932,
            "id": 299537,
            "video": false,
            "vote_average": 7.3,
            "title": "Captain Marvel",
            "popularity": 594.009,
            "poster_path": "/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg",
            "original_language": "en",
            "original_title": "Captain Marvel",
            "genre_ids": [
                28,
                12,
                878
            ],
            "backdrop_path": "/w2PMyoyLU22YvrGK3smVM9fW1jj.jpg",
            "adult": false,
            "overview": "The story follows Carol Danvers as she becomes one of the universe’s most powerful heroes when Earth is caught in the middle of a galactic war between two alien races. Set in the 1990s, Captain Marvel is an all-new adventure from a previously unseen period in the history of the Marvel Cinematic Universe.",
            "release_date": "2019-03-06"
        },
        {
            "vote_count": 1351,
            "id": 399579,
            "video": false,
            "vote_average": 6.7,
            "title": "Alita: Battle Angel",
            "popularity": 288.924,
            "poster_path": "/xRWht48C2V8XNfzvPehyClOvDni.jpg",
            "original_language": "en",
            "original_title": "Alita: Battle Angel",
            "genre_ids": [
                28,
                878,
                53,
                12
            ],
            "backdrop_path": "/aQXTw3wIWuFMy0beXRiZ1xVKtcf.jpg",
            "adult": false,
            "overview": "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
            "release_date": "2019-01-31"
        },
        {
            "vote_count": 4224,
            "id": 297802,
            "video": false,
            "vote_average": 6.8,
            "title": "Aquaman",
            "popularity": 241.812,
            "poster_path": "/5Kg76ldv7VxeX9YlcQXiowHgdX6.jpg",
            "original_language": "en",
            "original_title": "Aquaman",
            "genre_ids": [
                28,
                12,
                14,
                878
            ],
            "backdrop_path": "/5A2bMlLfJrAfX9bqAibOL2gCruF.jpg",
            "adult": false,
            "overview": "Once home to the most advanced civilization on Earth, the city of Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people -- and then the surface world. Standing in his way is Aquaman, Orm's half-human, half-Atlantean brother and true heir to the throne. With help from royal counselor Vulko, Aquaman must retrieve the legendary Trident of Atlan and embrace his destiny as protector of the deep.",
            "release_date": "2018-12-07"
        },
        {
            "vote_count": 924,
            "id": 166428,
            "video": false,
            "vote_average": 7.9,
            "title": "How to Train Your Dragon: The Hidden World",
            "popularity": 226.318,
            "poster_path": "/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg",
            "original_language": "en",
            "original_title": "How to Train Your Dragon: The Hidden World",
            "genre_ids": [
                16,
                10751,
                12
            ],
            "backdrop_path": "/h3KN24PrOheHVYs9ypuOIdFBEpX.jpg",
            "adult": false,
            "overview": "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
            "release_date": "2019-01-03"
        },
        {
            "vote_count": 729,
            "id": 449563,
            "video": false,
            "vote_average": 6.4,
            "title": "Isn't It Romantic",
            "popularity": 175.977,
            "poster_path": "/5xNBYXuv8wqiLVDhsfqCOr75DL7.jpg",
            "original_language": "en",
            "original_title": "Isn't It Romantic",
            "genre_ids": [
                35,
                14,
                10749
            ],
            "backdrop_path": "/fPwJmIg6CVNNXUJvL1wgeexzwEp.jpg",
            "adult": false,
            "overview": "For a long time, Natalie, an Australian architect living in New York City, had always believed that what she had seen in rom-coms is all fantasy. But after thwarting a mugger at a subway station only to be knocked out while fleeing, Natalie wakes up and discovers that her life has suddenly become her worst nightmare—a romantic comedy—and she is the leading lady.",
            "release_date": "2019-02-13"
        },
        {
            "vote_count": 2104,
            "id": 490132,
            "video": false,
            "vote_average": 8.3,
            "title": "Green Book",
            "popularity": 163.276,
            "poster_path": "/7BsvSuDQuoqhWmU2fL7W2GOcZHU.jpg",
            "original_language": "en",
            "original_title": "Green Book",
            "genre_ids": [
                18,
                35,
                10402
            ],
            "backdrop_path": "/Arxw3x7Y1jmkYryy0CodKce0Cms.jpg",
            "adult": false,
            "overview": "Tony Lip, a bouncer in 1962, is hired to drive pianist Don Shirley on a tour through the Deep South in the days when African Americans, forced to find alternate accommodations and services due to segregation laws below the Mason-Dixon Line, relied on a guide called The Negro Motorist Green Book.",
            "release_date": "2018-11-16"
        },
        {
            "vote_count": 55,
            "id": 445629,
            "video": false,
            "vote_average": 6.7,
            "title": "Fighting with My Family",
            "popularity": 147.371,
            "poster_path": "/3TZCBAdKQiz0cGKGEjZiyZUA01O.jpg",
            "original_language": "en",
            "original_title": "Fighting with My Family",
            "genre_ids": [
                35,
                18
            ],
            "backdrop_path": "/mYKP6qcDUimVMAHQWLOuDHjO19S.jpg",
            "adult": false,
            "overview": "Born into a tight-knit wrestling family, Paige and her brother Zak are ecstatic when they get the once-in-a-lifetime opportunity to try out for the WWE. But when only Paige earns a spot in the competitive training program, she must leave her loved ones behind and face this new cutthroat world alone. Paige's journey pushes her to dig deep and ultimately prove to the world that what makes her different is the very thing that can make her a star.",
            "release_date": "2019-02-14"
        },
        {
            "vote_count": 1632,
            "id": 480530,
            "video": false,
            "vote_average": 6.6,
            "title": "Creed II",
            "popularity": 143.602,
            "poster_path": "/v3QyboWRoA4O9RbcsqH8tJMe8EB.jpg",
            "original_language": "en",
            "original_title": "Creed II",
            "genre_ids": [
                18
            ],
            "backdrop_path": "/6JHYYbvoSuQ95ceGx8Oeg8zzAjg.jpg",
            "adult": false,
            "overview": "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
            "release_date": "2018-11-21"
        },
        {
            "vote_count": 923,
            "id": 400650,
            "video": false,
            "vote_average": 6.8,
            "title": "Mary Poppins Returns",
            "popularity": 143.304,
            "poster_path": "/uTVGku4LibMGyKgQvjBtv3OYfAX.jpg",
            "original_language": "en",
            "original_title": "Mary Poppins Returns",
            "genre_ids": [
                14,
                10751,
                35
            ],
            "backdrop_path": "/cwiJQXezWz876K3jS57Sq56RYCZ.jpg",
            "adult": false,
            "overview": "In Depression-era London, a now-grown Jane and Michael Banks, along with Michael's three children, are visited by the enigmatic Mary Poppins following a personal loss. Through her unique magical skills, and with the aid of her friend Jack, she helps the family rediscover the joy and wonder missing in their lives.",
            "release_date": "2018-12-13"
        },
        {
            "vote_count": 62,
            "id": 487297,
            "video": false,
            "vote_average": 6,
            "title": "What Men Want",
            "popularity": 142.915,
            "poster_path": "/30IiwvIRqPGjUV0bxJkZfnSiCL.jpg",
            "original_language": "en",
            "original_title": "What Men Want",
            "genre_ids": [
                35,
                14,
                10749
            ],
            "backdrop_path": "/umecegsPpKr2ZXA62Da9CQBVoIO.jpg",
            "adult": false,
            "overview": "Magically able to hear what men are thinking, a sports agent uses her newfound ability to turn the tables on her overbearing male colleagues.",
            "release_date": "2019-02-08"
        },
        {
            "vote_count": 306,
            "id": 512196,
            "video": false,
            "vote_average": 6.2,
            "title": "Happy Death Day 2U",
            "popularity": 139.345,
            "poster_path": "/whtt9F8PFqvEgc4fDSHZPkitFk4.jpg",
            "original_language": "en",
            "original_title": "Happy Death Day 2U",
            "genre_ids": [
                27,
                9648,
                53,
                878,
                35
            ],
            "backdrop_path": "/7trJGwprMUMKhvSx94lmsq6d985.jpg",
            "adult": false,
            "overview": "Collegian Tree Gelbman wakes up in horror to learn that she's stuck in a parallel universe. Her boyfriend Carter is now with someone else, and her friends and fellow students seem to be completely different versions of themselves. When Tree discovers that Carter's roommate has been altering time, she finds herself once again the target of a masked killer. When the psychopath starts to go after her inner circle, Tree soon realizes that she must die over and over again to save everyone.",
            "release_date": "2019-02-13"
        },
        {
            "vote_count": 42,
            "id": 450001,
            "video": false,
            "vote_average": 5.8,
            "title": "Master Z: Ip Man Legacy",
            "popularity": 138.198,
            "poster_path": "/2WfjB6FUDTIBVI2y02UGbnHR82s.jpg",
            "original_language": "cn",
            "original_title": "葉問外傳：張天志",
            "genre_ids": [
                28
            ],
            "backdrop_path": "/nv4KsjnhcSIZtuw2mkT9IxoQ5oq.jpg",
            "adult": false,
            "overview": "After being defeated by Ip Man, Cheung Tin Chi is attempting to keep a low profile. While going about his business, he gets into a fight with a foreigner by the name of Davidson, who is a big boss behind the bar district. Tin Chi fights hard with Wing Chun and earns respect.",
            "release_date": "2018-12-20"
        },
        {
            "vote_count": 369,
            "id": 491418,
            "video": false,
            "vote_average": 7.5,
            "title": "Instant Family",
            "popularity": 105.319,
            "poster_path": "/xYV1mODz99w7AjKDSQ7h2mzZhVe.jpg",
            "original_language": "en",
            "original_title": "Instant Family",
            "genre_ids": [
                35,
                18
            ],
            "backdrop_path": "/lwICpzZudw8BZ0bODaHgRWCdioB.jpg",
            "adult": false,
            "overview": "When Pete and Ellie decide to start a family, they stumble into the world of foster care adoption. They hope to take in one small child but when they meet three siblings, including a rebellious 15 year old girl, they find themselves speeding from zero to three kids overnight.",
            "release_date": "2018-11-16"
        },
        {
            "vote_count": 1777,
            "id": 404368,
            "video": false,
            "vote_average": 7.3,
            "title": "Ralph Breaks the Internet",
            "popularity": 101.318,
            "poster_path": "/lvfIaThG5HA8THf76nghKinjjji.jpg",
            "original_language": "en",
            "original_title": "Ralph Breaks the Internet",
            "genre_ids": [
                10751,
                16,
                35,
                14
            ],
            "backdrop_path": "/88poTBTafMXaz73vYi3c74g0y2k.jpg",
            "adult": false,
            "overview": "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, \"Sugar Rush.\" In way over their heads, Ralph and Vanellope rely on the citizens of the internet -- the netizens -- to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
            "release_date": "2018-11-20"
        },
        {
            "vote_count": 123,
            "id": 452832,
            "video": false,
            "vote_average": 4.9,
            "title": "Serenity",
            "popularity": 89.124,
            "poster_path": "/hgWAcic93phg4DOuQ8NrsgQWiqu.jpg",
            "original_language": "en",
            "original_title": "Serenity",
            "genre_ids": [
                53,
                18,
                9648,
                878
            ],
            "backdrop_path": "/oOROXoQ402tHgK6NowmMUSLffkW.jpg",
            "adult": false,
            "overview": "Baker Dill is a fishing boat captain leading tours off a tranquil, tropical enclave called Plymouth Island. His quiet life is shattered, however, when his ex-wife Karen tracks him down with a desperate plea for help.",
            "release_date": "2019-01-24"
        },
        {
            "vote_count": 217,
            "id": 438650,
            "video": false,
            "vote_average": 5.3,
            "title": "Cold Pursuit",
            "popularity": 88.963,
            "poster_path": "/hXgmWPd1SuujRZ4QnKLzrj79PAw.jpg",
            "original_language": "en",
            "original_title": "Cold Pursuit",
            "genre_ids": [
                53,
                28,
                18
            ],
            "backdrop_path": "/7Wg6FOEvhduISlxSzSCutAl4lPq.jpg",
            "adult": false,
            "overview": "Nels Coxman's quiet life comes crashing down when his beloved son dies under mysterious circumstances. His search for the truth soon becomes a quest for revenge as he seeks coldblooded justice against a drug lord and his inner circle.",
            "release_date": "2019-02-07"
        },
        {
            "vote_count": 250,
            "id": 280217,
            "video": false,
            "vote_average": 6.9,
            "title": "The Lego Movie 2: The Second Part",
            "popularity": 78.582,
            "poster_path": "/QTESAsBVZwjtGJNDP7utiGV37z.jpg",
            "original_language": "en",
            "original_title": "The Lego Movie 2: The Second Part",
            "genre_ids": [
                28,
                12,
                16,
                35,
                10751,
                878,
                14
            ],
            "backdrop_path": "/8kPozGb4BDrcWBSsGPrkULG2tP9.jpg",
            "adult": false,
            "overview": "It's been five years since everything was awesome and the citizens are facing a huge new threat: LEGO DUPLO® invaders from outer space, wrecking everything faster than they can rebuild.",
            "release_date": "2019-01-26"
        },
        {
            "vote_count": 10,
            "id": 474214,
            "video": false,
            "vote_average": 5.6,
            "title": "Trading Paint",
            "popularity": 78.432,
            "poster_path": "/yV9G1nO6YxEWzXblqMUFoS19R9v.jpg",
            "original_language": "en",
            "original_title": "Trading Paint",
            "genre_ids": [
                28
            ],
            "backdrop_path": "/7yCJIBlOJYial2srRLq1Wtxy6II.jpg",
            "adult": false,
            "overview": "The love and rivalry between a racing veteran and his fellow driver son creates many conflicts for a family.",
            "release_date": "2019-02-22"
        },
        {
            "vote_count": 785,
            "id": 504172,
            "video": false,
            "vote_average": 6.5,
            "title": "The Mule",
            "popularity": 75.66,
            "poster_path": "/oeZh7yEz3PMnZLgBPhrafFHRbVz.jpg",
            "original_language": "en",
            "original_title": "The Mule",
            "genre_ids": [
                80,
                18,
                53
            ],
            "backdrop_path": "/bTeRgkAavyw1eCtSkaww18wLYNP.jpg",
            "adult": false,
            "overview": "Earl Stone, a man in his 80s who is broke, alone, and facing foreclosure of his business when he is offered a job that simply requires him to drive. Easy enough, but, unbeknownst to Earl, he’s just signed on as a drug courier for a Mexican cartel. He does so well that his cargo increases exponentially, and Earl hit the radar of hard-charging DEA agent Colin Bates.",
            "release_date": "2018-12-14"
        },
        {
            "vote_count": 1483,
            "id": 375262,
            "video": false,
            "vote_average": 7.7,
            "title": "The Favourite",
            "popularity": 69.021,
            "poster_path": "/cwBq0onfmeilU5xgqNNjJAMPfpw.jpg",
            "original_language": "en",
            "original_title": "The Favourite",
            "genre_ids": [
                18,
                36,
                35
            ],
            "backdrop_path": "/ekWMoBZ4B9rM60INZEh5FAD2HFR.jpg",
            "adult": false,
            "overview": "England, early 18th century. The close relationship between Queen Anne and Sarah Churchill is threatened by the arrival of Sarah's cousin, Abigail Hill, resulting in a bitter rivalry between the two cousins to be the Queen's favourite.",
            "release_date": "2018-11-23"
        }
    ]"""

    fun generateDummyMovies(): List<Movie> {
        val gson:Gson = GsonBuilder().setLenient().create()

        return gson.fromJson<List<Movie>>(dummyResponse, object : TypeToken<List<Movie>>() {}.type)
    }
}