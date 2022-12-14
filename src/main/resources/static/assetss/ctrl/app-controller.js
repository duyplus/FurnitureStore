const app = angular.module("myApp", ['ngRoute', 'ui.bootstrap']);
app.config(function ($routeProvider, $locationProvider) {
    // route
    $routeProvider
        .when("/category-list", { templateUrl: "admin/pages/category-list.html", controller: "category-ctrl" })
        .when("/category-form", { templateUrl: "admin/pages/category-form.html", controller: "category-ctrl" })

        .when("/company-list", { templateUrl: "admin/pages/company-list.html", controller: "company-ctrl" })
        .when("/company-form", { templateUrl: "admin/pages/company-form.html", controller: "company-ctrl" })

        .when("/product-list", { templateUrl: "admin/pages/product-list.html", controller: "product-ctrl" })
        .when("/product-form", { templateUrl: "admin/pages/product-form.html", controller: "product-detail-ctrl" })

        .when("/order-approval", { templateUrl: "admin/pages/order-approval.html", controller: "order-form-ctrl" })
        .when("/store-list", { templateUrl: "admin/pages/store-list.html", controller: "store-ctrl" })
        .when("/store-form", { templateUrl: "admin/pages/store-form.html", controller: "store-ctrl" })

        .when("/stock-list", { templateUrl: "admin/pages/stock-list.html", controller: "stock-ctrl" })
        .when("/stock-form", { templateUrl: "admin/pages/stock-form.html", controller: "stock-ctrl" })

        .when("/order-cancel", { templateUrl: "admin/pages/order-cancel.html", controller: "order-ctrl" })
        .when("/order-form", { templateUrl: "admin/pages/order-form.html", controller: "order-ctrl" })
        .when("/order-list", { templateUrl: "admin/pages/order-list.html", controller: "order-ctrl" })
        .when("/orderdetail", { templateUrl: "admin/pages/orderdetail.html", controller: "order-ctrl" })

        .when("/review-list", { templateUrl: "admin/pages/review-list.html", controller: "review-ctrl" })
        .when("/review-form", { templateUrl: "admin/pages/review-form.html", controller: "review-ctrl" })

        .when("/user-list", { templateUrl: "admin/pages/user-list.html", controller: "user-ctrl" })
        .when("/user-form", { templateUrl: "admin/pages/user-form.html", controller: "user-form-ctrl" })

        .when("/customer-list", { templateUrl: "admin/pages/customer-list.html", controller: "customer-ctrl" })
        .when("/customer-form", { templateUrl: "admin/pages/customer-form.html", controller: "customer-form-ctrl" })

        .when("/statistic", { templateUrl: "admin/pages/statistic.html", controller: "statistic-ctrl" })
        .when("/setting", { templateUrl: "admin/pages/setting.html", controller: "setting-ctrl" })

        .when("/login", { templateUrl: "admin/pages/login.html" })
        .when("/404", { templateUrl: "admin/pages/404.html" })
        .when('/', { templateUrl: 'admin/pages/home.html', })
        .otherwise({ redirectTo: '/' });
});


app.factory('stockService', function () {
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

app.factory('storeService', function () {
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

app.factory('categoryService', function () {
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

app.directive("owlCarousel", function () {
    return {
        restrict: "E",
        transclude: false,
        link: function (scope) {
            scope.initCarousel = function (element) {
                var defaultOptions = {};
                var customOptions = scope.$eval($(element).attr("data-options"));
                for (var key in customOptions) {
                    defaultOptions[key] = customOptions[key];
                }
                var curOwl = $(element).data("owlCarousel");
                if (!angular.isDefined(curOwl)) {
                    $(element).owlCarousel(defaultOptions);
                }
                scope.cnt++;
            };
        },
    };
}).directive("owlCarouselItem", [
    function () {
        return {
            restrict: "A",
            transclude: false,
            link: function (scope, element) {
                if (scope.$last) {
                    scope.initCarousel(element.parent());
                }
            },
        };
    },
]);

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
app.factory('companyService', function () {
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