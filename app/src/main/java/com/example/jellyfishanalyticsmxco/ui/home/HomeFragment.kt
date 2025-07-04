package com.example.jellyfishanalyticsmxco.ui.home
// ECOMMERCE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jellyfishanalyticsmxco.databinding.FragmentHomeBinding
import com.google.firebase.analytics.FirebaseAnalytics
//GEMINI STARTS
import com.google.firebase.ktx.Firebase
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
//GEMINI ENDS

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    //GEMINI STARTS
    // Declara la instancia de Firebase Analytics
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    //GEMINI ENDS

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    //GEMINI STARTS
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // JF: Starts Firebase Analytics
        firebaseAnalytics = Firebase.analytics

        val itemOne = Bundle().apply {
            putString(FirebaseAnalytics.Param.ITEM_ID, "product_sku_one")
            putString(FirebaseAnalytics.Param.ITEM_NAME, "product_one")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "product_category_one")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY2, "product_category2_one")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY3, "product_category3_one")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY4, "product_category4_one")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY5, "product_category5_one")
            putString(FirebaseAnalytics.Param.ITEM_VARIANT, "product_variant_one")
            putString(FirebaseAnalytics.Param.ITEM_BRAND, "product_brand_one")
            putDouble(FirebaseAnalytics.Param.PRICE, 100.0)
            putDouble(FirebaseAnalytics.Param.DISCOUNT, 10.0)
        }

        val itemTwo = Bundle().apply {
            putString(FirebaseAnalytics.Param.ITEM_ID, "product_sku_two")
            putString(FirebaseAnalytics.Param.ITEM_NAME, "product_two")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "product_category_two")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY2, "product_category2_two")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY3, "product_category3_two")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY4, "product_category4_two")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY5, "product_category5_two")
            putString(FirebaseAnalytics.Param.ITEM_VARIANT, "product_variant_two")
            putString(FirebaseAnalytics.Param.ITEM_BRAND, "product_brand_two")
            putDouble(FirebaseAnalytics.Param.PRICE, 200.0)
        }

        val itemThree = Bundle().apply {
            putString(FirebaseAnalytics.Param.ITEM_ID, "product_sku_three")
            putString(FirebaseAnalytics.Param.ITEM_NAME, "product_three")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "product_category_three")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY2, "product_category2_three")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY3, "product_category3_three")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY4, "product_category4_three")
            putString(FirebaseAnalytics.Param.ITEM_CATEGORY5, "product_category5_three")
            putString(FirebaseAnalytics.Param.ITEM_VARIANT, "product_variant_three")
            putString(FirebaseAnalytics.Param.ITEM_BRAND, "product_brand_three")
            putDouble(FirebaseAnalytics.Param.PRICE, 300.0)
            putDouble(FirebaseAnalytics.Param.DISCOUNT, 30.0)
        }

        // JF: items with index
        val itemOneWithIndex = Bundle(itemOne).apply {
            putLong(FirebaseAnalytics.Param.INDEX, 1)
        }

        val itemTwoWithIndex = Bundle(itemTwo).apply {
            putLong(FirebaseAnalytics.Param.INDEX, 2)
        }

        val itemThreeWithIndex = Bundle(itemThree).apply {
            putLong(FirebaseAnalytics.Param.INDEX, 3)
        }

        // JF: items with quantity and coupon
        val itemOneWithQuantity = Bundle(itemOne).apply {
            putLong(FirebaseAnalytics.Param.INDEX, 1)
            putLong(FirebaseAnalytics.Param.QUANTITY, 1)
            putString(FirebaseAnalytics.Param.COUPON, "item_coupon_one")
        }

        val itemTwoWithQuantity = Bundle(itemTwo).apply {
            putLong(FirebaseAnalytics.Param.INDEX, 2)
            putLong(FirebaseAnalytics.Param.QUANTITY, 2)
        }

        val itemThreeWithQuantity = Bundle(itemThree).apply {
            putLong(FirebaseAnalytics.Param.INDEX, 3)
            putLong(FirebaseAnalytics.Param.QUANTITY, 3)
            putString(FirebaseAnalytics.Param.COUPON, "item_coupon_three")
        }

        // JF: button events
        //view_item_list
        binding.sendViewItemListBtn.setOnClickListener {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST) {
                param(FirebaseAnalytics.Param.ITEM_LIST_ID, "item_list_id")
                param(FirebaseAnalytics.Param.ITEM_LIST_NAME, "item_list_name")
                param(
                    FirebaseAnalytics.Param.ITEMS,
                    arrayOf(itemOneWithIndex, itemTwoWithIndex, itemThreeWithIndex),
                )
            }
        }

        //select_item
        binding.sendSelectItemBtn.setOnClickListener {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
                param(FirebaseAnalytics.Param.ITEM_LIST_ID, "item_list_id")
                param(FirebaseAnalytics.Param.ITEM_LIST_NAME, "item_list_name")
                param(FirebaseAnalytics.Param.ITEMS, arrayOf(itemOne))
            }
        }

        //view_item
        binding.sendViewItemBtn.setOnClickListener {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM) {
                param(FirebaseAnalytics.Param.CURRENCY, "MXN")
                param(FirebaseAnalytics.Param.VALUE, 100)
                param(FirebaseAnalytics.Param.ITEMS, arrayOf(itemOne))
            }
        }

        //add_to_cart
        binding.sendAddToCartBtn.setOnClickListener {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART) {
                param(FirebaseAnalytics.Param.CURRENCY, "MXN")
                param(FirebaseAnalytics.Param.VALUE, 100)
                param(FirebaseAnalytics.Param.ITEMS, arrayOf(itemOne))
            }
        }

        //view_cart
        binding.sendViewCartBtn.setOnClickListener {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_CART) {
                param(FirebaseAnalytics.Param.CURRENCY, "MXN")
                param(FirebaseAnalytics.Param.VALUE, 1400)
                param(
                    FirebaseAnalytics.Param.ITEMS,
                    arrayOf(itemOneWithQuantity, itemTwoWithQuantity, itemThreeWithQuantity),
                )
            }
        }

        //begin_checkout
        binding.sendBeginCheckoutBtn.setOnClickListener {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.BEGIN_CHECKOUT) {
                param(FirebaseAnalytics.Param.CURRENCY, "MXN")
                param(FirebaseAnalytics.Param.VALUE, 1400)
                param(FirebaseAnalytics.Param.COUPON, "event_coupon")
                param(
                    FirebaseAnalytics.Param.ITEMS,
                    arrayOf(itemOneWithQuantity, itemTwoWithQuantity, itemThreeWithQuantity),
                )
            }
        }

        //add_shipping_info
        binding.sendAddShippingInfoBtn.setOnClickListener {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_SHIPPING_INFO) {
                param(FirebaseAnalytics.Param.CURRENCY, "MXN")
                param(FirebaseAnalytics.Param.VALUE, 1400)
                param(FirebaseAnalytics.Param.COUPON, "event_coupon")
                param(FirebaseAnalytics.Param.SHIPPING_TIER, "shipping_tier_value")
                param(
                    FirebaseAnalytics.Param.ITEMS,
                    arrayOf(itemOneWithQuantity, itemTwoWithQuantity, itemThreeWithQuantity),
                )
            }
        }

        //add_payment_info
        binding.sendAddPaymentInfoBtn.setOnClickListener {
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_PAYMENT_INFO) {
                param(FirebaseAnalytics.Param.CURRENCY, "MXN")
                param(FirebaseAnalytics.Param.VALUE, 1400)
                param(FirebaseAnalytics.Param.COUPON, "event_coupon")
                param(FirebaseAnalytics.Param.SHIPPING_TIER, "shipping_tier_value")
                param(FirebaseAnalytics.Param.PAYMENT_TYPE, "payment_type_value")
                param(
                    FirebaseAnalytics.Param.ITEMS,
                    arrayOf(itemOneWithQuantity, itemTwoWithQuantity, itemThreeWithQuantity),
                )
            }
        }

        //purchase
        binding.sendPurchaseBtn.setOnClickListener {
            val purchaseIdValue = binding.purchaseID.text.toString()
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.PURCHASE) {
                param(FirebaseAnalytics.Param.TRANSACTION_ID, purchaseIdValue)
                param(FirebaseAnalytics.Param.CURRENCY, "MXN")
                param(FirebaseAnalytics.Param.VALUE, 1400)
                param(FirebaseAnalytics.Param.TAX, 224)
                param(FirebaseAnalytics.Param.SHIPPING, 10)
                param(FirebaseAnalytics.Param.COUPON, "event_coupon")
                param(FirebaseAnalytics.Param.SHIPPING_TIER, "shipping_tier_value")
                param(FirebaseAnalytics.Param.PAYMENT_TYPE, "payment_type_value")
                param(
                    FirebaseAnalytics.Param.ITEMS,
                    arrayOf(itemOneWithQuantity, itemTwoWithQuantity, itemThreeWithQuantity),
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, "ecommerce_screen")
            param(FirebaseAnalytics.Param.SCREEN_CLASS, "MainActivity")
        }
    }
    //GEMINI ENDS


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}