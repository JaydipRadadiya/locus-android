/*
 * Copyright © 2019 Birju Vachhani (https://github.com/BirjuVachhani)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.birjuvachhani.locationextension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

/*
 * Created by Birju Vachhani on 08 February 2019
 * Copyright © 2019 locus-android. All rights reserved.
 */

class LazyFragmentManager(private val instance: Any) : Lazy<FragmentManager> {

    private lateinit var manager: FragmentManager
    val tag = instance::class.java.simpleName

    override val value: FragmentManager
        get() {
            if (!isInitialized()) {
                manager = retrieveFragmentManager(instance)
            }
            return manager
        }

    override fun isInitialized(): Boolean = ::manager.isInitialized

    private fun retrieveFragmentManager(instance: Any): FragmentManager {
        return when (instance) {
            is FragmentActivity -> instance.supportFragmentManager
            is Fragment -> instance.childFragmentManager
            else -> throw Exception("No FragmentManager found in class ${instance::class.java.simpleName}")
        }
    }
}