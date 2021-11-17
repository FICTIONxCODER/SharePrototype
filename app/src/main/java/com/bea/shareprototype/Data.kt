package com.bea.shareprototype

//  Created by BEA on 2021.
//  Copyright © 2021 BEA. All rights reserved.


class Data {
    var map: Map<String, Map<String, String>>? = null

    constructor() {}
    constructor(map: Map<String, Map<String, String>>?) {
        this.map = map
    }

}