<?php
require 'vendor/autoload.php';
if(isset($_POST['authKey']) && isset($_POST['authKey']) == 'abc') {
    $stripe = new \Stripe\StripeClient('sk_test_51NNFfKKEjjyTT4MxJbhTU41HjnIKGGlwDdySTPbpkvnBRL3AKhKZtEY33XlhUKKl8mymM1UARUVy2tnZg5O6akxf00TrEnTa9q');

    $customer = $stripe->customers->create(
        [
            'name' => "Aleks",
            'address' => [
                'line1' => 'Demo Address',
                'postal_code' => 'NW72QL',
                'city' => 'New York',
                'state' => 'NY',
                'country' => 'US',

            ]
        ]
    );
    $ephemeralKey = $stripe->ephemeralKeys->create([
      'customer' => $customer->id,
    ], [
      'stripe_version' => '2022-08-01',
    ]);
    $paymentIntent = $stripe->paymentIntents->create([
      'amount' => 1099,
      'currency' => 'usd',
      'description' => 'Payment for item',
      'customer' => $customer->id,
      // In the latest version of the API, specifying the `automatic_payment_methods` parameter is optional because Stripe enables its functionality by default.
      'automatic_payment_methods' => [
        'enabled' => 'true',
      ],
    ]);

    echo json_encode(
      [
        'paymentIntent' => $paymentIntent->client_secret,
        'ephemeralKey' => $ephemeralKey->secret,
        'customer' => $customer->id,
        'publishableKey' => 'pk_test_51NNFfKKEjjyTT4MxXL5Hle8qe8A2I2Zv3aqF1g1JPcPPpd9CGVvpSc6BsJBnhEV5XRFzimhT80N391NWrIlCKmRQ00wFPYfafO'
      ]
    );
    http_response_code(200);
} else {
    echo "Not authenticated"
}