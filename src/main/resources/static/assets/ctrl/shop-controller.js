app.controller("shop-ctrl", function ($scope, $http) {
    $scope.slickConfig = {
        arrows: false,
        autoplay: false,
        autoplaySpeed: 5000,
        dots: true,
        pauseOnFocus: false,
        pauseOnHover: false,
        fade: true,
        infinite: true,
        slidesToShow: 1,
        responsive: [
            {
                breakpoint: 767,
                settings: {
                    dots: true,
                },
            },
        ],
    };
})