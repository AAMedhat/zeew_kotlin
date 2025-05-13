package com.example.zeew

import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zeew.adapter.MenuItemAdapter
import com.example.zeew.data.RestaurantData
import com.example.zeew.model.MenuItem
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.util.Locale
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.widget.NestedScrollView
import com.google.android.material.card.MaterialCardView
import android.graphics.Color
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.core.view.WindowInsetsCompat
import android.widget.HorizontalScrollView

/**
 * MainActivity serves as the main screen of the Zeew restaurant app.
 * It displays the restaurant's menu items, handles category navigation,
 * manages the shopping cart, and provides a responsive UI that adapts
 * to different screen sizes and orientations.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var menuItemAdapter: MenuItemAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var chipGroup: ChipGroup
    private lateinit var categoriesContainer: MaterialCardView
    private lateinit var viewCartButton: MaterialButton
    private var isUserScrolling = false
    private val cartItems = mutableMapOf<MenuItem, Int>() // MenuItem to quantity map
    private var selectedCategoryView: View? = null
    
    /**
     * Initializes the activity, sets up the UI components and their behaviors.
     * This includes setting up the window decorations, status bar, restaurant info,
     * menu categories, RecyclerView for menu items, and the shopping cart.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupWindowAndStatusBar()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        setupRestaurantInfo()
        setupCategories()
        setupRecyclerView()
        setupViewCartButton()
        setupScrollBehavior()

        setupAppBarTransition()
        setupStatusBarPadding()
    }

    /**
     * Sets up the restaurant information section including the header image,
     * logo, name, description, rating, delivery fee, and delivery time.
     */
    private fun setupRestaurantInfo() {
        // Set restaurant header image
        val headerImage = findViewById<ImageView>(R.id.headerImage)
        headerImage.setImageResource(RestaurantData.restaurantInfo["headerResId"] as Int)

        // Set restaurant logo
        val logoImage = findViewById<ImageView>(R.id.restaurantLogo)
        logoImage.setImageResource(RestaurantData.restaurantInfo["logoResId"] as Int)

        // Set text information
        findViewById<TextView>(R.id.restaurantName).text = RestaurantData.restaurantInfo["name"] as String
        findViewById<TextView>(R.id.restaurantDescription).text = RestaurantData.restaurantInfo["description"] as String
        findViewById<RatingBar>(R.id.ratingBar).rating = (RestaurantData.restaurantInfo["rating"] as Double).toFloat()

        // Set delivery info
        val deliveryFee = findViewById<TextView>(R.id.deliveryFee)
        deliveryFee.text = "Delivery fee: AED ${RestaurantData.restaurantInfo["deliveryFee"]}"

        val deliveryTime = findViewById<TextView>(R.id.deliveryTime)
        deliveryTime.text = RestaurantData.restaurantInfo["deliveryTime"] as String
    }

    /**
     * Configures the scroll behavior for the NestedScrollView and categories container.
     * Adds elevation to the categories container when the user scrolls down.
     */
    private fun setupScrollBehavior() {
        val scrollView = findViewById<NestedScrollView>(R.id.nestedScrollView)
        categoriesContainer = findViewById(R.id.categoriesContainer)
        
        scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // Add elevation to categories container when scrolled
            val elevation = if (scrollY > 0) 8f else 4f
            categoriesContainer.elevation = elevation
        })
    }

    /**
     * Sets up the category chips that allow users to filter menu items by category.
     * Each category chip includes a bottom line indicator that shows when selected.
     */
    private fun setupCategories() {
        chipGroup = findViewById(R.id.categoryChipGroup)
        chipGroup.removeAllViews()
        
        RestaurantData.categories.forEachIndexed { index, category ->
            val chip = Chip(this).apply {
                text = category.name
                isCheckable = true
                isChecked = index == 0
                setOnClickListener {
                    updateCategorySelection(this, category.name)
                }
            }
            
            // Add bottom line indicator (initially invisible)
            val indicator = View(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    2.dpToPx()
                ).apply {
                    topMargin = (-4).dpToPx()
                }
                background = ContextCompat.getDrawable(context, R.drawable.category_indicator)
                visibility = if (index == 0) View.VISIBLE else View.INVISIBLE
            }
            
            val container = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                addView(chip)
                addView(indicator)
            }
            
            if (index == 0) {
                selectedCategoryView = indicator
            }
            
            chipGroup.addView(container)
        }
    }

    /**
     * Updates the selected category and scrolls the menu items to show items
     * from the selected category. Also handles the visual feedback of selection.
     * 
     * @param selectedChip The chip that was selected by the user
     * @param category The category name that was selected
     */
    private fun updateCategorySelection(selectedChip: Chip, category: String) {
        // Hide previous indicator
        selectedCategoryView?.visibility = View.INVISIBLE
        
        // Show new indicator
        val container = selectedChip.parent as? LinearLayout
        val indicator = container?.getChildAt(1)
        indicator?.visibility = View.VISIBLE
        selectedCategoryView = indicator
        
        // Scroll the category chip to the start
        val horizontalScrollView = (chipGroup.parent as? HorizontalScrollView)
        horizontalScrollView?.smoothScrollTo(container?.left ?: 0, 0)
        
        // Find the first item of the selected category
        val items = menuItemAdapter.getItems()
        val position = items.indexOfFirst { it.category == category }
        
        if (position != -1) {
            // Get the NestedScrollView
            val scrollView = findViewById<NestedScrollView>(R.id.nestedScrollView)
            
            // First, scroll the RecyclerView
            recyclerView.stopScroll() // Stop any ongoing scroll
            (recyclerView.layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(position, 0)
            
            // Then, ensure the RecyclerView item is fully visible
            recyclerView.post {
                val itemView = recyclerView.layoutManager?.findViewByPosition(position)
                if (itemView != null) {
                    val itemTop = itemView.top
                    val scrollViewHeight = scrollView.height
                    val targetScroll = itemTop - (scrollViewHeight / 4) // Position item at 1/4 of the screen
                    
                    // Smooth scroll to the calculated position
                    scrollView.smoothScrollBy(0, targetScroll)
                }
            }
        }
    }

    /**
     * Utility function to convert density-independent pixels (dp) to pixels (px).
     * 
     * @return The pixel value corresponding to the dp input
     */
    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }

    /**
     * Sets up the RecyclerView that displays menu items. Handles both list and
     * grid layouts depending on the device type and orientation. Also sets up
     * scroll listeners to update category selection based on visible items.
     */
    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.menuItemsRecyclerView)
        
        val layoutManager = if (isTablet() && resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(this, 2)
        } else {
            LinearLayoutManager(this)
        }
        recyclerView.layoutManager = layoutManager

        menuItemAdapter = MenuItemAdapter(RestaurantData.menuItems) { menuItem ->
            addItemToCart(menuItem)
        }
        recyclerView.adapter = menuItemAdapter

        // Add scroll listener to update category chips
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                isUserScrolling = newState != RecyclerView.SCROLL_STATE_IDLE
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!isUserScrolling) return

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if (firstVisibleItemPosition != RecyclerView.NO_POSITION) {
                    val category = menuItemAdapter.getItems()[firstVisibleItemPosition].category
                    updateSelectedChip(category)
                }
            }
        })
    }

    /**
     * Updates the selected category chip based on the currently visible menu items
     * while scrolling through the RecyclerView.
     * 
     * @param category The category name to select
     */
    private fun updateSelectedChip(category: String) {
        for (i in 0 until chipGroup.childCount) {
            val chip = chipGroup.getChildAt(i) as? Chip
            if (chip?.text == category) {
                if (!chip.isChecked) {
                    isUserScrolling = false
                    chip.isChecked = true
                }
                break
            }
        }
    }

    /**
     * Sets up the view cart button that shows the current number of items
     * and total price. Also handles the click event to show cart contents.
     */
    private fun setupViewCartButton() {
        viewCartButton = findViewById(R.id.viewCartButton)
        updateCartButton()
        
        viewCartButton.setOnClickListener {
            val itemsList = cartItems.entries.joinToString("\n") { (item, quantity) ->
                "${item.name} x$quantity: AED ${NumberFormat.getInstance(Locale.US).format(item.price * quantity)}"
            }
            Snackbar.make(it, "Cart Items:\n$itemsList", Snackbar.LENGTH_LONG).show()
        }
    }

    /**
     * Adds a menu item to the shopping cart and updates the cart button.
     * Shows a Snackbar notification when an item is added.
     * 
     * @param menuItem The menu item to add to the cart
     */
    private fun addItemToCart(menuItem: MenuItem) {
        cartItems[menuItem] = (cartItems[menuItem] ?: 0) + 1
        updateCartButton()
        Snackbar.make(findViewById(android.R.id.content), "${menuItem.name} added to cart", Snackbar.LENGTH_SHORT).show()
    }

    /**
     * Updates the view cart button text and state based on the current
     * contents of the shopping cart.
     */
    private fun updateCartButton() {
        val itemCount = cartItems.values.sum()
        val totalPrice = cartItems.entries.sumOf { (item, quantity) -> item.price * quantity }
        
        if (itemCount > 0) {
            viewCartButton.text = "$itemCount items • View Cart • AED ${NumberFormat.getInstance(Locale.US).format(totalPrice)}"
            viewCartButton.isEnabled = true
        } else {
            viewCartButton.text = "View Cart"
            viewCartButton.isEnabled = false
        }
    }

    /**
     * Determines if the current device is a tablet based on screen width.
     * 
     * @return true if the device is a tablet, false otherwise
     */
    private fun isTablet(): Boolean {
        return resources.configuration.screenWidthDp >= 600
    }

    /**
     * Handles configuration changes (e.g., orientation changes) by
     * reinitializing the RecyclerView with appropriate layout.
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setupRecyclerView()
    }

    /**
     * Interpolates between two colors based on a fraction value.
     * Used for smooth color transitions in the app bar.
     * 
     * @param startColor The starting color
     * @param endColor The ending color
     * @param fraction The interpolation fraction (0.0 to 1.0)
     * @return The interpolated color
     */
    private fun interpolateColor(startColor: Int, endColor: Int, fraction: Float): Int {
        val startA = Color.alpha(startColor)
        val startR = Color.red(startColor)
        val startG = Color.green(startColor)
        val startB = Color.blue(startColor)

        val endA = Color.alpha(endColor)
        val endR = Color.red(endColor)
        val endG = Color.green(endColor)
        val endB = Color.blue(endColor)

        return Color.argb(
            (startA + (endA - startA) * fraction).toInt(),
            (startR + (endR - startR) * fraction).toInt(),
            (startG + (endG - startG) * fraction).toInt(),
            (startB + (endB - startB) * fraction).toInt()
        )
    }

    /**
     * Sets up the window and status bar to be transparent and edge-to-edge.
     */
    private fun setupWindowAndStatusBar() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
        }
    }

    /**
     * Configures the status bar padding to ensure proper layout with
     * edge-to-edge content.
     */
    private fun setupStatusBarPadding() {
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(top = insets.top)
            windowInsets
        }
    }

    /**
     * Sets up the app bar transition effects when scrolling, including
     * color changes for the toolbar, status bar, and buttons.
     */
    private fun setupAppBarTransition() {
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        val toolbarTitle = findViewById<TextView>(R.id.toolbarTitle)
        val backButton = findViewById<MaterialCardView>(R.id.backButton)
        val favoriteButton = findViewById<MaterialCardView>(R.id.favoriteButton)
        val shareButton = findViewById<MaterialCardView>(R.id.shareButton)
        val searchButton = findViewById<MaterialCardView>(R.id.searchButton)

        val scrollView = findViewById<NestedScrollView>(R.id.nestedScrollView)
        scrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            // Calculate the transition threshold
            val threshold = 200f // Height of header image
            val ratio = (scrollY.coerceAtMost(threshold.toInt()) / threshold).coerceIn(0f, 1f)

            // Update toolbar background
            toolbar.setBackgroundColor(interpolateColor(Color.TRANSPARENT, Color.WHITE, ratio))

            // Update status bar
            window.statusBarColor = interpolateColor(Color.TRANSPARENT, Color.WHITE, ratio)
            WindowInsetsControllerCompat(window, window.decorView).apply {
                isAppearanceLightStatusBars = ratio > 0.5
            }

            // Update text color from transparent to black
            toolbarTitle.setTextColor(interpolateColor(Color.TRANSPARENT, Color.BLACK, ratio))

            // Update button backgrounds from semi-transparent to white
            val buttonBackgroundColor = interpolateColor(
                Color.parseColor("#1F000000"),
                Color.parseColor("#F5F5F5"),
                ratio
            )
            backButton.setCardBackgroundColor(buttonBackgroundColor)
            favoriteButton.setCardBackgroundColor(buttonBackgroundColor)
            shareButton.setCardBackgroundColor(buttonBackgroundColor)
            searchButton.setCardBackgroundColor(buttonBackgroundColor)
        }
    }
}