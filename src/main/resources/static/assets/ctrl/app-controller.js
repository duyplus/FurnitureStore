const app = angular.module("myApp", ['ngRoute']);
app.config(function ($routeProvider, $locationProvider) {
    // route
    $routeProvider
        .when("/", { templateUrl: "pages/home.html", controller: "home-ctrl" })
        .when("/shop", { templateUrl: "pages/shop.html", controller: "shop-ctrl" })
        .when("/cart", { templateUrl: "pages/cart.html", controller: "cart-ctrl" })
        .when("/product", { templateUrl: "pages/product.html", controller: "product-ctrl" })
        .when("/checkout", { templateUrl: "pages/checkout.html", controller: "checkout-ctrl" })
        .when("/wishlist", { templateUrl: "pages/wishlist.html", controller: "wishlist-ctrl" })
        .when("/my-account", { templateUrl: "pages/my-account.html", controller: "user-ctrl" })
        .when("/forgot-password", { templateUrl: "pages/forgot-password.html", controller: "user-ctrl" })
        .when("/reset-password", { templateUrl: "pages/reset-password.html", controller: "user-ctrl" })
        .when("/register", { templateUrl: "pages/register.html", controller: "user-ctrl" })
        .when("/login", { templateUrl: "pages/login.html", controller: "user-ctrl" })

        .when("/contact", { templateUrl: "pages/contact.html" })
        .when("/about", { templateUrl: "pages/about.html" })
        .when("/blog", { templateUrl: "pages/blog.html" })
        .when("/404", { templateUrl: "pages/404.html" })
        .otherwise({ redirectTo: "/" });
});

app.constant("HOST", "http://localhost:8080");

app.directive('convertDate', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
            ngModel.$formatters.push(function (fromModel) {
                fromModel = new Date(fromModel);
                return fromModel;
            });
            ngModel.$parsers.push(function (fromField) {
                fromField = fromField.getTime();
                return fromField;
            });
        }
    }
});

app.factory('myService', function () {
    var savedData = {}
    function set(data) {
        savedData = data;
    }
    function get() {
        return savedData;
    }
    return {
        set: set,
        get: get
    }
});