package com.isyed.samples

import android.os.Bundle
import android.os.RecoverySystem
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val TAG = "MenuFragment"
class MenuFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rv_Menus : RecyclerView
    private lateinit var menuItems : MutableList<MenuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        rv_Menus = view.findViewById(R.id.rv_menus)
        return view
    }

    override fun onStart() {
        super.onStart()

        //create dummy menu items
        menuItems = mutableListOf()
        menuItems.addAll(mutableListOf(
        MenuItem("Gunkan Maki", "Makizushi, also known as “norimaki,” refers to a type of sushi where rice and ingredients are carefully rolled in a sheet of nori seaweed, which is then cut into smaller pieces. It’s believed that makizushi came into existence in the early 1700s, soon after sheet nori was invented with a similar technique used for paper making. The name norimaki is made up of two Japanese words: “Maki” meaning to roll and “nori” referring to the toasted sheet of nori seaweed used to wrap the ingredients.",
        11.99, "https://gurunavi.com/en/japanfoodie/article/types_of_sushi/img/01_Sushi.jpg")
                ,
        MenuItem("Temaki","Temaki is a novel type of sushi with a shape resembling that of an ice cream cone. To make it, rice and ingredients are held within a sheet of nori wrapped into a conical shape. It’s popular at restaurants, as well as for making at home, given its simplicity. Temaki lends itself to a variety of fillings, with some popular types including umeshiso—a paste made of fresh shiso leaf and umeboshi (pickled plum), negitoro, squid with and without natto, and sweetened omelet.\n"
        ,11.99, "https://gurunavi.com/en/japanfoodie/article/types_of_sushi/img/03_Sushi.jpg")
        ,
        MenuItem("Nigiri",
        "Nigiri is the original form of sushi that we know today. Also called edo-mae (meaning “in front of Edo”), the name refers to its birthplace of Tokyo (formerly Edo). It’s made up of a hand-pressed rice cylinder (shari) topped with any number of ingredients (neta). It’s believed to have been invented as a type of “fast food” by an enterprising sushi chef working in the Edo area during the 1800s who decided to sell his freshly created sushi to nearby workers for a quick snack. The topping can be seafood, vegetables, meat, omelet, or tofu, and in addition to fresh seafood, the fish may be pickled in soy sauce or vinegar, or broiled with a blowtorch. A simple coating of marinade and garnishes such as spring onions, shaved onion, or chives may also be added.",11.99, "https://gurunavi.com/en/japanfoodie/article/types_of_sushi/img/05_Sushi.jpg")
        ,
        MenuItem("In-N-Out Burger",
            "In-N-Out Burger — Yes, it's a chain with hundreds of locations. But no burger chain inspires a fan fervor quite like the California-based In-N-Out, which has been serving the same simple burger (thin-patty, topped with lettuce, tomato, and maybe cheese or onions) since 1948.",
        22.99, "https://cdn.vox-cdn.com/thumbor/JeOJkDr8WN7U8A1a2Z6CLVh9azc=/0x0:1024x684/1120x0/filters:focal(0x0:1024x684):format(webp):no_upscale()/cdn.vox-cdn.com/uploads/chorus_asset/file/3617586/in-n-out-essentialsolares.0.png")
        ,
        MenuItem("Au Cheval",
            "Au Cheval — According to Bill Addison, this haute Chicago diner burger, which boasts two beef patties on the \"single\" hamburger, represents \"one of the country's best examples of the flattop-griddled burger hoisted to haute levels without losing sight of its diner roots.\"",
        12.33, "https://cdn.vox-cdn.com/thumbor/FrIPKvT9I1faI05dSmXTJtiABFE=/0x0:789x526/1120x0/filters:focal(0x0:789x526):format(webp):no_upscale()/cdn.vox-cdn.com/uploads/chorus_asset/file/3615952/aucheval-chicago.0.jpg")
        ,
        MenuItem("The General Muir",
        "The General Muir — This Atlanta restaurant, an elegantly re-imagined take on the traditional New York City deli, serves two different versions of its burger. At lunch, there's what Addison calls a \"tamer version\" of chef Todd Ginsberg's much-heralded burger at his",11.99, "https://gurunavi.com/en/japanfoodie/article/types_of_sushi/img/01_Sushi.jpg")
        ,
            MenuItem("Gunkan Maki", "Makizushi, also known as “norimaki,” refers to a type of sushi where rice and ingredients are carefully rolled in a sheet of nori seaweed, which is then cut into smaller pieces. It’s believed that makizushi came into existence in the early 1700s, soon after sheet nori was invented with a similar technique used for paper making. The name norimaki is made up of two Japanese words: “Maki” meaning to roll and “nori” referring to the toasted sheet of nori seaweed used to wrap the ingredients.",
                11.99, "https://gurunavi.com/en/japanfoodie/article/types_of_sushi/img/01_Sushi.jpg")
            ,
            MenuItem("Temaki","Temaki is a novel type of sushi with a shape resembling that of an ice cream cone. To make it, rice and ingredients are held within a sheet of nori wrapped into a conical shape. It’s popular at restaurants, as well as for making at home, given its simplicity. Temaki lends itself to a variety of fillings, with some popular types including umeshiso—a paste made of fresh shiso leaf and umeboshi (pickled plum), negitoro, squid with and without natto, and sweetened omelet.\n"
                ,11.99, "https://gurunavi.com/en/japanfoodie/article/types_of_sushi/img/03_Sushi.jpg")
            ,
            MenuItem("Nigiri",
                "Nigiri is the original form of sushi that we know today. Also called edo-mae (meaning “in front of Edo”), the name refers to its birthplace of Tokyo (formerly Edo). It’s made up of a hand-pressed rice cylinder (shari) topped with any number of ingredients (neta). It’s believed to have been invented as a type of “fast food” by an enterprising sushi chef working in the Edo area during the 1800s who decided to sell his freshly created sushi to nearby workers for a quick snack. The topping can be seafood, vegetables, meat, omelet, or tofu, and in addition to fresh seafood, the fish may be pickled in soy sauce or vinegar, or broiled with a blowtorch. A simple coating of marinade and garnishes such as spring onions, shaved onion, or chives may also be added.",11.99, "https://gurunavi.com/en/japanfoodie/article/types_of_sushi/img/05_Sushi.jpg")
            ,
            MenuItem("In-N-Out Burger",
                "In-N-Out Burger — Yes, it's a chain with hundreds of locations. But no burger chain inspires a fan fervor quite like the California-based In-N-Out, which has been serving the same simple burger (thin-patty, topped with lettuce, tomato, and maybe cheese or onions) since 1948.",
                22.99, "https://cdn.vox-cdn.com/thumbor/JeOJkDr8WN7U8A1a2Z6CLVh9azc=/0x0:1024x684/1120x0/filters:focal(0x0:1024x684):format(webp):no_upscale()/cdn.vox-cdn.com/uploads/chorus_asset/file/3617586/in-n-out-essentialsolares.0.png")
            ,
            MenuItem("Au Cheval",
                "Au Cheval — According to Bill Addison, this haute Chicago diner burger, which boasts two beef patties on the \"single\" hamburger, represents \"one of the country's best examples of the flattop-griddled burger hoisted to haute levels without losing sight of its diner roots.\"",
                12.33, "https://cdn.vox-cdn.com/thumbor/FrIPKvT9I1faI05dSmXTJtiABFE=/0x0:789x526/1120x0/filters:focal(0x0:789x526):format(webp):no_upscale()/cdn.vox-cdn.com/uploads/chorus_asset/file/3615952/aucheval-chicago.0.jpg")
            ,MenuItem("Gunkan Maki", "Makizushi, also known as “norimaki,” refers to a type of sushi where rice and ingredients are carefully rolled in a sheet of nori seaweed, which is then cut into smaller pieces. It’s believed that makizushi came into existence in the early 1700s, soon after sheet nori was invented with a similar technique used for paper making. The name norimaki is made up of two Japanese words: “Maki” meaning to roll and “nori” referring to the toasted sheet of nori seaweed used to wrap the ingredients.",
                11.99, "https://gurunavi.com/en/japanfoodie/article/types_of_sushi/img/01_Sushi.jpg")
            ,
            MenuItem("Temaki","Temaki is a novel type of sushi with a shape resembling that of an ice cream cone. To make it, rice and ingredients are held within a sheet of nori wrapped into a conical shape. It’s popular at restaurants, as well as for making at home, given its simplicity. Temaki lends itself to a variety of fillings, with some popular types including umeshiso—a paste made of fresh shiso leaf and umeboshi (pickled plum), negitoro, squid with and without natto, and sweetened omelet.\n"
                ,11.99, "https://gurunavi.com/en/japanfoodie/article/types_of_sushi/img/03_Sushi.jpg")
            ,
            MenuItem("Nigiri",
                "Nigiri is the original form of sushi that we know today. Also called edo-mae (meaning “in front of Edo”), the name refers to its birthplace of Tokyo (formerly Edo). It’s made up of a hand-pressed rice cylinder (shari) topped with any number of ingredients (neta). It’s believed to have been invented as a type of “fast food” by an enterprising sushi chef working in the Edo area during the 1800s who decided to sell his freshly created sushi to nearby workers for a quick snack. The topping can be seafood, vegetables, meat, omelet, or tofu, and in addition to fresh seafood, the fish may be pickled in soy sauce or vinegar, or broiled with a blowtorch. A simple coating of marinade and garnishes such as spring onions, shaved onion, or chives may also be added.",11.99, "https://gurunavi.com/en/japanfoodie/article/types_of_sushi/img/05_Sushi.jpg")
            ,
            MenuItem("In-N-Out Burger",
                "In-N-Out Burger — Yes, it's a chain with hundreds of locations. But no burger chain inspires a fan fervor quite like the California-based In-N-Out, which has been serving the same simple burger (thin-patty, topped with lettuce, tomato, and maybe cheese or onions) since 1948.",
                22.99, "https://cdn.vox-cdn.com/thumbor/JeOJkDr8WN7U8A1a2Z6CLVh9azc=/0x0:1024x684/1120x0/filters:focal(0x0:1024x684):format(webp):no_upscale()/cdn.vox-cdn.com/uploads/chorus_asset/file/3617586/in-n-out-essentialsolares.0.png")
            ,
            MenuItem("Au Cheval",
                "Au Cheval — According to Bill Addison, this haute Chicago diner burger, which boasts two beef patties on the \"single\" hamburger, represents \"one of the country's best examples of the flattop-griddled burger hoisted to haute levels without losing sight of its diner roots.\"",
                12.33, "https://cdn.vox-cdn.com/thumbor/FrIPKvT9I1faI05dSmXTJtiABFE=/0x0:789x526/1120x0/filters:focal(0x0:789x526):format(webp):no_upscale()/cdn.vox-cdn.com/uploads/chorus_asset/file/3615952/aucheval-chicago.0.jpg")

        ))

        //initialize recyclerview
        rv_Menus.layoutManager = LinearLayoutManager(activity)
        rv_Menus.adapter = FoodItemAdapter(menuItems, {} , {  })
    }
    companion object {
        fun newInstance() =
            MenuFragment()
//                .apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
            }
    }
